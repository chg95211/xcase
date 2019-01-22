/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.box.impl.simple.methods;

import com.xcase.box.constant.BoxConstant;
import com.xcase.box.factories.BoxObjectFactory;
import com.xcase.box.factories.BoxResponseFactory;
import com.xcase.box.objects.BoxException;
import com.xcase.box.objects.BoxUser;
import com.xcase.box.transputs.GetAuthorizationRequest;
import com.xcase.box.transputs.GetAuthorizationResponse;
import com.xcase.common.constant.CommonConstant;
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
public class GetAuthorizationMethod extends BaseBoxMethod {

    /**
     * log4j object.
     */
    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    ;

    /**
     *
     */
    public GetAuthorizationMethod() {
        super();
    }

    /**
     *
     * @param getAuthorizationRequest request
     * @return response
     * @throws IOException IO exception
     * @throws BoxException box exception
     */
    public GetAuthorizationResponse getAuthorization(GetAuthorizationRequest getAuthorizationRequest) throws IOException, BoxException {
        LOGGER.debug("starting getAuthorization()");
        GetAuthorizationResponse getAuthorizationResponse = BoxResponseFactory.createGetAuthorizationResponse();
        String apiKey = getAuthorizationRequest.getApiKey();
        if (BoxConstant.CONFIG_API_REQUEST_FORMAT_REST.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is " + BoxConstant.CONFIG_API_REQUEST_FORMAT_REST);
            StringBuffer urlBuff = new StringBuffer(super.xmlOAuthApiUrl);
            urlBuff.append(CommonConstant.QUESTION_MARK_STRING);
            urlBuff.append(BoxConstant.PARAM_NAME_RESPONSE_TYPE);
            urlBuff.append(CommonConstant.EQUALS_SIGN_STRING);
            urlBuff.append("code");
            urlBuff.append(CommonConstant.AND_SIGN_STRING);
            urlBuff.append(BoxConstant.PARAM_NAME_CLIENT_ID);
            urlBuff.append(CommonConstant.EQUALS_SIGN_STRING);
            urlBuff.append(apiKey);
            try {
                String urlString = urlBuff.toString();
                LOGGER.debug("urlString is " + urlString);
                String entityString = httpManager.doStringGet(urlBuff.toString());
                Document doc = DocumentHelper.parseText(entityString);
                LOGGER.debug("done Get");
                Element responseElement = doc.getRootElement();
                LOGGER.debug("responseElement is " + responseElement.getText());
                Element statusElement = responseElement.element(BoxConstant.PARAM_NAME_STATUS);
                String status = statusElement.getText();
                LOGGER.debug("status is " + status);
                getAuthorizationResponse.setStatus(status);
                if (BoxConstant.STATUS_GET_AUTH_TOKEN_OK.equals(status)) {
                    LOGGER.debug("status is OK");
                    Element authTokenElement = responseElement.element(BoxConstant.PARAM_NAME_AUTH_TOKEN);
                    Element userElement = responseElement.element(BoxConstant.PARAM_NAME_USER);
                    String authToken = authTokenElement.getText();
                    Element loginElement = userElement.element(BoxConstant.PARAM_NAME_LOGIN);
                    Element emailElement = userElement.element(BoxConstant.PARAM_NAME_EMAIL);
                    Element accessIdElm = userElement.element(BoxConstant.PARAM_NAME_ACCESS_ID);
                    Element userIdElm = userElement.element(BoxConstant.PARAM_NAME_USER_ID);
                    Element spaceAmountElm = userElement.element(BoxConstant.PARAM_NAME_SPACE_AMOUNT);
                    Element spaceUsedElm = userElement.element(BoxConstant.PARAM_NAME_SPACE_USED);
                    getAuthorizationResponse.setAuthToken(authToken);
                    BoxUser user = BoxObjectFactory.createBoxUser();
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
                    getAuthorizationResponse.setUser(user);
                }
            } catch (Exception e) {
                throw new BoxException("failed to parse to a document.", e);
            }
        } else if (BoxConstant.CONFIG_API_REQUEST_FORMAT_XML.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is " + BoxConstant.CONFIG_API_REQUEST_FORMAT_XML);
            Document document = DocumentHelper.createDocument();
            Element requestElm = DocumentHelper.createElement(BoxConstant.PARAM_NAME_REQUEST);
            document.add(requestElm);
            Element actionElm = DocumentHelper.createElement(BoxConstant.PARAM_NAME_ACTION);
            Element apiKeyElm = DocumentHelper.createElement(BoxConstant.PARAM_NAME_API_KEY);
            Element clientIDElm = DocumentHelper.createElement(BoxConstant.PARAM_NAME_CLIENT_ID);
            actionElm.setText(BoxConstant.ACTION_NAME_GET_AUTH_TOKEN);
            apiKeyElm.setText(apiKey);
            clientIDElm.setText(apiKey);
            requestElm.add(actionElm);
            requestElm.add(apiKeyElm);
            requestElm.add(clientIDElm);
            LOGGER.debug("xmlOAuthApiUrl is " + xmlOAuthApiUrl);
            try {
                String result = httpManager.doStringPost(xmlOAuthApiUrl, document.asXML());
                LOGGER.debug("result is " + result);
                Document doc = DocumentHelper.parseText(result);
                Element responseElement = doc.getRootElement();
                LOGGER.debug("responseElm is " + responseElement.toString());
                Element statusElm = responseElement.element(BoxConstant.PARAM_NAME_STATUS);
                String status = statusElm.getText();
                getAuthorizationResponse.setStatus(status);
                if (BoxConstant.STATUS_GET_AUTH_TOKEN_OK.equals(status)) {
                    Element authTokenElm = responseElement.element(BoxConstant.PARAM_NAME_AUTH_TOKEN);
                    Element userElm = responseElement.element(BoxConstant.PARAM_NAME_USER);
                    String authToken = authTokenElm.getText();
                    Element loginElm = userElm.element(BoxConstant.PARAM_NAME_LOGIN);
                    Element emailElm = userElm.element(BoxConstant.PARAM_NAME_EMAIL);
                    Element accessIdElm = userElm.element(BoxConstant.PARAM_NAME_ACCESS_ID);
                    Element userIdElm = userElm.element(BoxConstant.PARAM_NAME_USER_ID);
                    Element spaceAmountElm = userElm.element(BoxConstant.PARAM_NAME_SPACE_AMOUNT);
                    Element spaceUsedElm = userElm.element(BoxConstant.PARAM_NAME_SPACE_USED);
                    getAuthorizationResponse.setAuthToken(authToken);
                    BoxUser user = BoxObjectFactory.createBoxUser();
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
                    getAuthorizationResponse.setUser(user);
                }
            } catch (Exception e) {
                throw new BoxException("GetAuthorizationMethod: failed to parse to a document.", e);
            }
        } else if (BoxConstant.CONFIG_API_REQUEST_FORMAT_SOAP.equals(apiRequestFormat)) {
            LOGGER.debug("apiRequestFormat is BoxConstant.CONFIG_API_REQUEST_FORMAT_SOAP");
        } else {
            LOGGER.debug("apiRequestFormat is unrecognized");
        }

        return getAuthorizationResponse;
    }
}
