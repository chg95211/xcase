/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.salesforce.impl.simple.methods;

import com.xcase.common.constant.CommonConstant;
import com.xcase.common.impl.simple.core.CommonHTTPManager;
import com.xcase.salesforce.constant.SalesforceConstant;
import com.xcase.salesforce.impl.simple.core.SalesforceConfigurationManager;
import java.lang.invoke.*;
import java.util.Properties;
import org.apache.logging.log4j.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author martin
 *
 */
public class BaseSalesforceMethod {

    /**
     * log4j logger.
     */
    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * core http manager.
     */
    protected CommonHTTPManager httpManager = CommonHTTPManager.refreshCommonHTTPManager();

    /**
     * the configuration.
     */
    protected Properties config = SalesforceConfigurationManager.getConfigurationManager().getConfig();

    /**
     * the configuration.
     */
    protected Properties localConfig = SalesforceConfigurationManager.getConfigurationManager().getLocalConfig();

    /**
     * API OAuth authorize prefix
     */
    protected String apiOAuthAuthorizePrefix;

    /**
     * API OAuth revoke prefix
     */
    protected String apiOAuthRevokePrefix;

    /**
     * API OAuth authorize prefix
     */
    protected String apiOAuthTokenPrefix;

    /**
     * API URL prefix.
     */
    protected String apiUrlPrefix;

    /**
     * API upload URL prefix.
     */
    protected String apiUploadUrlPrefix;

    /**
     * API version.
     */
    protected String apiVersion;

    /**
     * API request format.
     */
    protected String apiRequestFormat;

    /**
     * OAuth authorize XML URL, it's static, so no need to read each time.
     */
    protected String xmlOAuthApiUrl;

    /**
     * OAuth authorize XML URL, it's static, so no need to read each time.
     */
    protected String xmlOAuthRevokeUrl;

    /**
     * OAuth authorize XML URL, it's static, so no need to read each time.
     */
    protected String xmlOAuthTokenUrl;

    /**
     * API XML URL, it's static, so no need to read each time.
     */
    protected String xmlApiUrl;

    /**
     * API SOAP URL, it's static, so no need to read each time.
     */
    protected String soapApiUrl;

    /**
     *
     */
    public BaseSalesforceMethod() {
        LOGGER.debug("starting BaseSalesforceMethod()");
        this.apiOAuthTokenPrefix = config.getProperty(SalesforceConstant.CONFIG_API_OAUTH_TOKEN_PREFIX);
        LOGGER.debug("apiOAuthTokenPrefix is " + apiOAuthTokenPrefix);
        this.apiOAuthAuthorizePrefix = config.getProperty(SalesforceConstant.CONFIG_API_OAUTH_AUTHORIZE_PREFIX);
        LOGGER.debug("apiOAuthAuthorizePrefix is " + apiOAuthAuthorizePrefix);
        this.apiOAuthRevokePrefix = config.getProperty(SalesforceConstant.CONFIG_API_OAUTH_REVOKE_PREFIX);
        LOGGER.debug("apiOAuthAuthorizePrefix is " + apiOAuthAuthorizePrefix);
        this.apiUrlPrefix = localConfig.getProperty(SalesforceConstant.LOCAL_OAUTH2_INSTANCE_URL);
        LOGGER.debug("apiUrlPrefix is " + apiUrlPrefix);
        this.apiUploadUrlPrefix = config.getProperty(SalesforceConstant.CONFIG_API_UPLOAD_URL_PREFIX);
        this.apiVersion = config.getProperty(SalesforceConstant.CONFIG_API_VERSION);
        this.apiRequestFormat = config.getProperty(SalesforceConstant.CONFIG_API_REQUEST_FORMAT);
        this.xmlApiUrl = getXMLUrl();
        this.soapApiUrl = getSOAPUrl();
        this.xmlOAuthApiUrl = getOAuthAuthorizeUrl();
        this.xmlOAuthRevokeUrl = getOAuthRevokeUrl();
        this.xmlOAuthTokenUrl = getOAuthTokenUrl();
    }

    /**
     * According to action name, return a string buffer. i.e. "get_ticket" can
     * result a "http://www.box.net/api/1.0/rest?action=get_ticket"
     *
     * @param actionName action name
     * @return the URL in string buffer
     */
    public StringBuffer getRestUrl(String actionName) {
        LOGGER.debug("starting getRestUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(this.apiUrlPrefix);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(this.apiVersion);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(SalesforceConstant.CONFIG_API_REQUEST_FORMAT_REST);
        urlBuf.append(CommonConstant.QUESTION_MARK_STRING);
        urlBuf.append(CommonConstant.ACTION_EQUALS_STRING);
        urlBuf.append(actionName);
        return urlBuf;
    }

    /**
     * According to action name, return a string buffer. i.e. "get_ticket" can
     * result a "http://www.box.net/api/1.0/rest?action=get_ticket"
     *
     * @param actionType
     * @return the URL in string buffer
     */
    public StringBuffer getApiUrl(String actionType) {
        LOGGER.debug("starting getApiUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(this.apiUrlPrefix);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append("services/data");
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(this.apiVersion);
        urlBuf.append(CommonConstant.SLASH_STRING);
        //urlBuf.append("sobjects");
        //urlBuf.append(SalesforceConstant.SLASH_STRING);
        urlBuf.append(actionType);
        return urlBuf;
    }

    /**
     * get the XML format URL. like: "http://www.box.net/api/1.0/xml"
     *
     * @return URL string
     */
    private String getXMLUrl() {
        LOGGER.debug("starting getXMLUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(apiUrlPrefix);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(apiVersion);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(SalesforceConstant.CONFIG_API_REQUEST_FORMAT_XML);
        //LOGGER.debug("BaseBoxMethod: urlBuf is " + urlBuf.toString());
        return urlBuf.toString();
    }

    /**
     * get the OAuth URL. like: "https://www.box.com/api/oauth2/authorize"
     *
     * @return URL string
     */
    private String getOAuthAuthorizeUrl() {
        LOGGER.debug("starting getOAuthAuthorizeUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(apiOAuthAuthorizePrefix);
//        urlBuf.append(BoxConstant.SLASH_STRING);
//        urlBuf.append(apiVersion);
//        urlBuf.append(BoxConstant.SLASH_STRING);
//        urlBuf.append(BoxConstant.CONFIG_API_REQUEST_FORMAT_XML);
        //LOGGER.debug("BaseBoxMethod: urlBuf is " + urlBuf.toString());
        return urlBuf.toString();
    }

    private String getOAuthRevokeUrl() {
        LOGGER.debug("starting getOAuthRevokeUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(apiOAuthRevokePrefix);
        return urlBuf.toString();
    }

    /**
     * get the OAuth URL. like: "https://www.box.com/api/oauth2/authorize"
     *
     * @return URL string
     */
    private String getOAuthTokenUrl() {
        LOGGER.debug("starting getOAuthTokenUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(apiOAuthTokenPrefix);
        //LOGGER.debug("BaseBoxMethod: urlBuf is " + urlBuf.toString());
        return urlBuf.toString();
    }

    /**
     * get the SOAP format URL. like: "http://box.net/api/1.0/soap"
     *
     * @return URL string
     */
    private String getSOAPUrl() {
        LOGGER.debug("starting getSOAPUrl()");
        StringBuffer urlBuf = new StringBuffer();
        urlBuf.append(apiUrlPrefix);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(apiVersion);
        urlBuf.append(CommonConstant.SLASH_STRING);
        urlBuf.append(SalesforceConstant.CONFIG_API_REQUEST_FORMAT_SOAP);
        //LOGGER.debug("BaseBoxMethod: urlBuf is " + urlBuf.toString());
        return urlBuf.toString();
//      return "http://box.net/api/1.0/soap";
    }

    /**
     * get prepared document.
     *
     * @return dom4j document
     */
    protected Document getBaseSoapDocument() {
        LOGGER.debug("starting getBaseSoapDocument()");
        Document doc = DocumentHelper.createDocument();
        Element envelopeElm = DocumentHelper.createElement(SalesforceConstant.PARAM_NAME_SOAP_ENVELOPE);
        envelopeElm.addAttribute("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelopeElm.addAttribute("xmlns:soapenc", "http://schemas.xmlsoap.org/soap/encoding/");
        envelopeElm.addAttribute("xmlns:tns", "urn:boxnet");
        envelopeElm.addAttribute("xmlns:types", "urn:boxnet/encodedTypes");
        envelopeElm.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        envelopeElm.addAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        doc.setRootElement(envelopeElm);
        Element bodyElm = DocumentHelper.createElement(SalesforceConstant.PARAM_NAME_SOAP_BODY);
        bodyElm.addAttribute("soap:encodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        envelopeElm.add(bodyElm);
        return doc;
    }

    /**
     *
     * @param actionName action name
     * @return element
     */
    protected Element getElementByActionName(String actionName) {
        LOGGER.debug("starting getElementByActionName()");
        Element elm = DocumentHelper.createElement("tns:" + actionName);
        return elm;
    }

    /**
     *
     * @param elmName element name
     * @param elmType element type
     * @return element
     */
    protected Element getSoapElement(String elmName, String elmType) {
        LOGGER.debug("starting getSoapElement()");
        Element elm = DocumentHelper.createElement(elmName);
        elm.addAttribute("xsi:type", elmType);
        return elm;
    }
}
