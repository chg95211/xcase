/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.impl.simple.methods;

import com.xcase.common.constant.CommonConstant;
import com.xcase.sharepoint.constant.SharepointConstant;
import com.xcase.sharepoint.factories.SharepointResponseFactory;
import com.xcase.sharepoint.objects.SharepointException;
import com.xcase.sharepoint.transputs.PublicShareRequest;
import com.xcase.sharepoint.transputs.PublicShareResponse;
import java.io.IOException;
import java.lang.invoke.*;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author martin
 *
 */
public class PublicShareMethod extends BaseSharepointMethod {

    /**
     * log4j logger.
     */
    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * This method publicly shares a file or folder. 'target' param should be
     * either 'file' or 'folder', 'target_id' is id of the file or folder to be
     * shared. 'password' param is to protect sharing with a password, 'emails'
     * params is array of emails to notify about a new share, 'message' param is
     * some message to be included in a notification email.
     *
     * On a successful result, the status will be 'share_ok' and 'public_name'
     * param will be a unique identifier of a publicly shared file or folder. If
     * the result wasn't successful, the status field can be: 'share_error',
     * 'wrong_node', 'not_logged_in', 'application_restricted'.
     *
     * @param publicShareRequest request
     * @return response
     * @throws IOException io exception
     * @throws SharepointException box exception
     */
    public PublicShareResponse publicShare(PublicShareRequest publicShareRequest) throws IOException, SharepointException {
        LOGGER.debug("starting publicShare()");
        PublicShareResponse publicShareResponse = SharepointResponseFactory.createPublicShareResponse();
        String apiKey = publicShareRequest.getApiKey();
        String accessToken = publicShareRequest.getAccessToken();
        LOGGER.debug("accessToken is " + accessToken);
        String authToken = publicShareRequest.getAuthToken();
        String message = publicShareRequest.getMessage();
        String target = publicShareRequest.getTarget();
        String targetId = publicShareRequest.getTargetId();
        String password = publicShareRequest.getPassword();
        String[] emails = publicShareRequest.getEmails();

        if (SharepointConstant.CONFIG_API_REQUEST_FORMAT_REST.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is " + SharepointConstant.CONFIG_API_REQUEST_FORMAT_REST);
            URLCodec codec = new URLCodec();
            targetId = codec.encode(targetId, "ISO-8859-1");
            StringBuffer urlBuff = super.getApiUrl("folders");
            String foldersApiUrl = urlBuff.toString();
            LOGGER.debug("foldersApiUrl is " + foldersApiUrl);
            urlBuff.append("/" + targetId);
            String targetApiUrl = urlBuff.toString();
            LOGGER.debug("targetApiUrl is " + targetApiUrl);
            String bearerString = "Bearer " + accessToken;
            LOGGER.debug("bearerString is " + bearerString);
            Header header = new BasicHeader("Authorization", bearerString);
            LOGGER.debug("created Authorization header");
            Header[] headers = {header};
            if (emails == null) {
                urlBuff.append(CommonConstant.AND_SIGN_STRING);
                urlBuff.append(SharepointConstant.PARAM_NAME_PARAMS_EMAILS);
                urlBuff.append(CommonConstant.EQUALS_SIGN_STRING);
            } else {
                for (int i = 0; i < emails.length; i++) {
                    String email = emails[i];
                    urlBuff.append(CommonConstant.AND_SIGN_STRING);
                    urlBuff.append(SharepointConstant.PARAM_NAME_PARAMS_EMAILS);
                    urlBuff.append(CommonConstant.EQUALS_SIGN_STRING);
                    urlBuff.append(email);
                }
            }

            try {
                String responseString = httpManager.doStringGet(targetApiUrl, headers, null);
                String status = "";
                publicShareResponse.setStatus(status);
                if (SharepointConstant.STATUS_SHARE_OK.equals(status)) {
                    String publicName = "";
                    publicShareResponse.setPublicName(publicName);
                }
            } catch (Exception e) {
                SharepointException be = new SharepointException("failed to parse to a document.", e);
                be.setStatus(publicShareResponse.getStatus());
                throw be;
            }
        } else if (SharepointConstant.CONFIG_API_REQUEST_FORMAT_XML.equals(apiRequestFormat)) {
            Document document = DocumentHelper.createDocument();
            Element requestElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_REQUEST);
            document.add(requestElm);
            Element actionElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_ACTION);
            Element apiKeyElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_API_KEY);
            Element authTokenElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_AUTH_TOKEN);
            Element targetElm = DocumentHelper
                    .createElement(SharepointConstant.PARAM_NAME_TARGET);
            Element targetIdElm = DocumentHelper
                    .createElement(SharepointConstant.PARAM_NAME_TARGET_ID);
            Element passwordElm = DocumentHelper
                    .createElement(SharepointConstant.PARAM_NAME_PASSWORD);
            Element messageElm = DocumentHelper
                    .createElement(SharepointConstant.PARAM_NAME_MESSAGE);
            Element emailsElm = DocumentHelper
                    .createElement(SharepointConstant.PARAM_NAME_EMAILS);
            requestElm.add(actionElm);
            requestElm.add(apiKeyElm);
            requestElm.add(authTokenElm);
            requestElm.add(targetElm);
            requestElm.add(targetIdElm);
            requestElm.add(passwordElm);
            requestElm.add(messageElm);
            requestElm.add(emailsElm);
            //
            actionElm.setText(SharepointConstant.ACTION_NAME_PUBLIC_SHARE);
            apiKeyElm.setText(apiKey);
            authTokenElm.setText(authToken);
            targetElm.setText(target);
            targetIdElm.setText(targetId);
            passwordElm.setText(password);
            messageElm.setText(message);
            if (emails != null) {
                for (int i = 0; i < emails.length; i++) {
                    String email = emails[i];
                    Element emailElm = DocumentHelper
                            .createElement(SharepointConstant.PARAM_NAME_ITEM);
                    emailElm.setText(email);
                    emailsElm.add(emailElm);
                }
            }

            try {
                String result = httpManager.doStringPost(xmlApiUrl, document.asXML());
                Document doc = DocumentHelper.parseText(result);
                Element responseElm = doc.getRootElement();
                Element statusElm = responseElm.element(SharepointConstant.PARAM_NAME_STATUS);
                String status = statusElm.getText();
                publicShareResponse.setStatus(status);
                if (SharepointConstant.STATUS_SHARE_OK.equals(status)) {
                    Element publicNameElm = responseElm.element(SharepointConstant.PARAM_NAME_PUBLIC_NAME);
                    String publicName = publicNameElm.getText();
                    publicShareResponse.setPublicName(publicName);
                }
            } catch (Exception e) {
                SharepointException be = new SharepointException("failed to parse to a document.", e);
                be.setStatus(publicShareResponse.getStatus());
                throw be;
            }
        } else if (SharepointConstant.CONFIG_API_REQUEST_FORMAT_SOAP.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is " + SharepointConstant.CONFIG_API_REQUEST_FORMAT_SOAP);
        } else {
            LOGGER.debug("apiRequestFormat is unrecognized " + apiRequestFormat);
        }

        return publicShareResponse;

    }
}
