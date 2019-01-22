/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.integrate.impl.simple.methods;

import com.xcase.integrate.objects.RuleInfo;
import com.xcase.integrate.objects.RuleLongExecuting;
import com.google.gson.*;
import com.xcase.common.constant.CommonConstant;
import com.xcase.common.impl.simple.core.CommonHttpResponse;
import com.xcase.common.utils.ConverterUtils;
import com.xcase.integrate.constant.IntegrateConstant;
import com.xcase.integrate.factories.IntegrateResponseFactory;
import com.xcase.integrate.transputs.GetRuleLongExecutingRequest;
import com.xcase.integrate.transputs.GetRuleLongExecutingResponse;
import java.io.StringReader;
import java.lang.invoke.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author martinpg
 */
public class GetRuleLongExecutingMethod extends BaseIntegrateMethod {

    /**
     * log4j object.
     */
    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private String endPoint;

    public GetRuleLongExecutingResponse getRuleLongExecuting(GetRuleLongExecutingRequest request) {
        LOGGER.debug("starting getRuleLongExecuting()");
        GetRuleLongExecutingResponse response = IntegrateResponseFactory.createGetRuleLongExecutingResponse();
        LOGGER.debug("created response");
        try {
            String baseVersionUrl = super.apiVersionUrl;
            LOGGER.debug("baseVersionUrl is " + baseVersionUrl);
            String object = "logs";
            String ruleLongExecutingPath = "rule/longexe";
            endPoint = baseVersionUrl + CommonConstant.SLASH_STRING + object + CommonConstant.SLASH_STRING + ruleLongExecutingPath;
            String accessToken = request.getAccessToken();
            LOGGER.debug("accessToken is " + accessToken);
            Header authorizationHeader = createIntegrateAuthenticationTokenHeader(accessToken);
            LOGGER.debug("created Authorization header");
            Header acceptHeader = createAcceptHeader();
            Header contentTypeHeader = createContentTypeHeader();
//            Header xHTTPMethodHeader = new BasicHeader(CommonConstant.X_HTTP_METHOD_STRING, "GET");
//            LOGGER.debug("created xHTTPMethodHeader header");
            Header[] headers = {acceptHeader, authorizationHeader, contentTypeHeader};
            CommonHttpResponse commonHttpResponse = httpManager.doCommonHttpResponseGet(endPoint, headers, null, null);
            int responseCode = commonHttpResponse.getResponseCode();
            LOGGER.debug("responseCode is " + responseCode);
            response.setResponseCode(responseCode);
            if (responseCode == 200) {
                String responseEntityString = commonHttpResponse.getResponseEntityString();
                LOGGER.debug("responseEntityString is " + responseEntityString);
                if (IntegrateConstant.CONFIG_API_REQUEST_FORMAT_JSON.equals(this.apiRequestFormat)) {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd' 'HH:mm:ss").create();
                    JsonObject responseEntityJsonObject = (JsonObject) ConverterUtils.parseStringToJson(responseEntityString);
                    LOGGER.debug("created getResponseJsonObject");
                    RuleLongExecuting ruleLongExecuting = gson.fromJson(responseEntityJsonObject, RuleLongExecuting.class);
                    LOGGER.debug("created ruleLongExecuting");
                    JsonArray ruleInfoArray = responseEntityJsonObject.getAsJsonArray("long_exe_rules");
                    Iterator<JsonElement> ruleInfoArrayIterator = ruleInfoArray.iterator();
                    List<RuleInfo> ruleInfoList = new ArrayList<RuleInfo>();
                    while (ruleInfoArrayIterator.hasNext()) {
                        RuleInfo ruleInfo = gson.fromJson(ruleInfoArrayIterator.next(), RuleInfo.class);
                        ruleInfoList.add(ruleInfo);
                    }

                    ruleLongExecuting.setRuleInfoList(ruleInfoList);
                    response.setRuleLongExecuting(ruleLongExecuting);
                } else if (IntegrateConstant.CONFIG_API_REQUEST_FORMAT_XML.equals(this.apiRequestFormat)) {
                    JAXBContext jaxbContext = JAXBContext.newInstance(RuleLongExecuting.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    StringReader stringReader = new StringReader(responseEntityString);
                    LOGGER.debug("created stringReader");
                    RuleLongExecuting ruleLongExecuting = (RuleLongExecuting) jaxbUnmarshaller.unmarshal(stringReader);
                    if (ruleLongExecuting != null) {
                        LOGGER.debug("created ruleLongExecuting");
                        response.setRuleLongExecuting(ruleLongExecuting);
                    } else {
                        LOGGER.warn("ruleLongExecuting is null");
                    }
                } else {
                    LOGGER.warn("unexpected API request format: " + this.apiRequestFormat);
                    response.setMessage("Unexpected API request format: " + this.apiRequestFormat);
                    response.setStatus("FAIL");
                }
            } else {
                handleUnexpectedResponseCode(response, commonHttpResponse);
            }
        } catch (Exception e) {
            handleUnexpectedException(response, e);
        }

        return response;
    }
}
