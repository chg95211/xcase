/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.impl.simple.methods;

import com.xcase.common.constant.CommonConstant;
import com.xcase.sharepoint.constant.SharepointConstant;
import com.xcase.sharepoint.factories.SharepointObjectFactory;
import com.xcase.sharepoint.factories.SharepointResponseFactory;
import com.xcase.sharepoint.objects.SharepointException;
import com.xcase.sharepoint.objects.SharepointUser;
import com.xcase.sharepoint.transputs.GetAuthTokenRequest;
import com.xcase.sharepoint.transputs.GetAuthTokenResponse;
import java.io.IOException;
import java.lang.invoke.*;
import org.apache.logging.log4j.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author martin
 *
 */
public class GetAuthTokenMethod extends BaseSharepointMethod {

    /**
     * log4j logger.
     */
    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     *
     */
    public GetAuthTokenMethod() {
        super();
    }

    /**
     *
     * @param getAuthTokenRequest request
     * @return response
     * @throws IOException IO exception
     * @throws SharepointException box exception
     */
    public GetAuthTokenResponse getAuthToken(GetAuthTokenRequest getAuthTokenRequest) throws IOException, SharepointException {
        LOGGER.debug("starting getAuthToken()");
        GetAuthTokenResponse getAuthTokenResponse = SharepointResponseFactory.createGetAuthTokenResponse();
        String apiKey = getAuthTokenRequest.getApiKey();
        String ticket = getAuthTokenRequest.getTicket();

        if (SharepointConstant.CONFIG_API_REQUEST_FORMAT_REST.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is SharepointConstant.CONFIG_API_REQUEST_FORMAT_REST");
            StringBuffer urlBuff = super.getRestUrl(SharepointConstant.ACTION_NAME_GET_AUTH_TOKEN);
            urlBuff.append(CommonConstant.AND_SIGN_STRING);
            urlBuff.append(SharepointConstant.PARAM_NAME_API_KEY);
            urlBuff.append(CommonConstant.EQUALS_SIGN_STRING);
            urlBuff.append(apiKey);
            urlBuff.append(CommonConstant.AND_SIGN_STRING);
            urlBuff.append(SharepointConstant.PARAM_NAME_TICKET);
            urlBuff.append(CommonConstant.EQUALS_SIGN_STRING);
            urlBuff.append(ticket);
            try {
                String entityString = httpManager.doStringGet(urlBuff.toString(), null, null, null);
                Document doc = DocumentHelper.parseText(entityString);
                LOGGER.debug("done Get");
                Element responseElement = doc.getRootElement();
                LOGGER.debug("responseElement is " + responseElement.getText());
                Element statusElement = responseElement.element(SharepointConstant.PARAM_NAME_STATUS);
                String status = statusElement.getText();
                getAuthTokenResponse.setStatus(status);
                if (SharepointConstant.STATUS_GET_AUTH_TOKEN_OK.equals(status)) {
                    LOGGER.debug("status is OK");
                    Element authTokenElement = responseElement.element(SharepointConstant.PARAM_NAME_AUTH_TOKEN);
                    Element userElement = responseElement.element(SharepointConstant.PARAM_NAME_USER);
                    String authToken = authTokenElement.getText();
                    Element loginElement = userElement.element(SharepointConstant.PARAM_NAME_LOGIN);
                    Element emailElement = userElement.element(SharepointConstant.PARAM_NAME_EMAIL);
                    Element accessIdElm = userElement.element(SharepointConstant.PARAM_NAME_ACCESS_ID);
                    Element userIdElm = userElement.element(SharepointConstant.PARAM_NAME_USER_ID);
                    Element spaceAmountElm = userElement.element(SharepointConstant.PARAM_NAME_SPACE_AMOUNT);
                    Element spaceUsedElm = userElement.element(SharepointConstant.PARAM_NAME_SPACE_USED);
                    getAuthTokenResponse.setAuthToken(authToken);
                    SharepointUser user = SharepointObjectFactory.createSharepointUser();
                    user.setLogin(loginElement.getText());
                    user.setEmail(emailElement.getText());
                    user.setAccessId(accessIdElm.getText());
                    user.setUserId(userIdElm.getText());
                    long spaceAmount = 0;
                    try {
                        spaceAmount = Long.parseLong(spaceAmountElm.getText());
                    } catch (NumberFormatException e) {
                        spaceAmount = Long.MIN_VALUE;
                    }

                    user.setSpaceAmount(spaceAmount);
                    long spaceUsed = 0;
                    try {
                        spaceUsed = Long.parseLong(spaceUsedElm.getText());
                    } catch (NumberFormatException e) {
                        spaceUsed = Long.MIN_VALUE;
                    }

                    user.setSpaceUsed(spaceUsed);
                    getAuthTokenResponse.setUser(user);
                }
            } catch (Exception e) {
                throw new SharepointException("failed to parse to a document.", e);
            }
        } else if (SharepointConstant.CONFIG_API_REQUEST_FORMAT_XML.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is SharepointConstant.CONFIG_API_REQUEST_FORMAT_XML");
            Document document = DocumentHelper.createDocument();
            Element requestElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_REQUEST);
            document.add(requestElm);
            Element actionElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_ACTION);
            Element apiKeyElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_API_KEY);
            Element ticketElm = DocumentHelper.createElement(SharepointConstant.PARAM_NAME_TICKET);
            requestElm.add(actionElm);
            requestElm.add(apiKeyElm);
            requestElm.add(ticketElm);
            actionElm.setText(SharepointConstant.ACTION_NAME_GET_AUTH_TOKEN);
            apiKeyElm.setText(apiKey);
            ticketElm.setText(ticket);
            try {
                String result = httpManager.doStringPost(xmlApiUrl, null, null, document.asXML(), null);
                LOGGER.debug("result is " + result);
                Document doc = DocumentHelper.parseText(result);
                Element responseElement = doc.getRootElement();
                LOGGER.debug("responseElm is " + responseElement.toString());
                Element statusElm = responseElement.element(SharepointConstant.PARAM_NAME_STATUS);
                String status = statusElm.getText();
                getAuthTokenResponse.setStatus(status);
                if (SharepointConstant.STATUS_GET_AUTH_TOKEN_OK.equals(status)) {
                    Element authTokenElm = responseElement.element(SharepointConstant.PARAM_NAME_AUTH_TOKEN);
                    Element userElm = responseElement.element(SharepointConstant.PARAM_NAME_USER);
                    String authToken = authTokenElm.getText();
                    Element loginElm = userElm.element(SharepointConstant.PARAM_NAME_LOGIN);
                    Element emailElm = userElm.element(SharepointConstant.PARAM_NAME_EMAIL);
                    Element accessIdElm = userElm.element(SharepointConstant.PARAM_NAME_ACCESS_ID);
                    Element userIdElm = userElm.element(SharepointConstant.PARAM_NAME_USER_ID);
                    Element spaceAmountElm = userElm.element(SharepointConstant.PARAM_NAME_SPACE_AMOUNT);
                    Element spaceUsedElm = userElm.element(SharepointConstant.PARAM_NAME_SPACE_USED);
                    getAuthTokenResponse.setAuthToken(authToken);
                    SharepointUser user = SharepointObjectFactory.createSharepointUser();
                    user.setLogin(loginElm.getText());
                    user.setEmail(emailElm.getText());
                    user.setAccessId(accessIdElm.getText());
                    user.setUserId(userIdElm.getText());
                    long spaceAmount = 0;
                    try {
                        spaceAmount = Long.parseLong(spaceAmountElm.getText());
                    } catch (NumberFormatException e) {
                        spaceAmount = Long.MIN_VALUE;
                    }

                    user.setSpaceAmount(spaceAmount);
                    long spaceUsed = 0;
                    try {
                        spaceUsed = Long.parseLong(spaceUsedElm.getText());
                    } catch (NumberFormatException e) {
                        spaceUsed = Long.MIN_VALUE;
                    }

                    user.setSpaceUsed(spaceUsed);
                    getAuthTokenResponse.setUser(user);
                }
            } catch (Exception e) {
                throw new SharepointException("GetAuthTokenMethod: failed to parse to a document.", e);
            }
        } else if (SharepointConstant.CONFIG_API_REQUEST_FORMAT_SOAP.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is SharepointConstant.CONFIG_API_REQUEST_FORMAT_SOAP");
        } else {
            LOGGER.debug("apiRequestFormat is unrecognized");
        }

        return getAuthTokenResponse;

    }
}
