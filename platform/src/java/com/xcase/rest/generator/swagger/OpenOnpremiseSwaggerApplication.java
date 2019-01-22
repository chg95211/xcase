package com.xcase.rest.generator.swagger;

import com.xcase.rest.generator.RESTServiceDefinition;
import com.xcase.rest.generator.RESTApiProxySettingsEndpoint;
import java.lang.invoke.*;
import java.net.*;
import java.util.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author martin
 */
public class OpenOnpremiseSwaggerApplication {
   /**
     * log4j object.
     */
    protected static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOGGER.debug("starting main()");
        try {
            OpenOnpremiseSwaggerProxy swaggerProxy = new OpenOnpremiseSwaggerProxy(new URL("http://catford/Open.Services.Rest/"), "Admin", "", "");
            LOGGER.debug("created swaggerProxy");
            swaggerProxy.buildHttpClient();
            LOGGER.debug("built HttpClient");
            String swaggerDocument = swaggerProxy.getSwaggerDocument();
            LOGGER.debug("swaggerDocument is " + swaggerDocument);
            String packageName = swaggerProxy.getClass().getPackage().getName();
            LOGGER.debug("packageName is " + packageName);
            SwaggerJavaProxyGenerator swaggerProxyGenerator = new SwaggerJavaProxyGenerator(packageName);
            RESTApiProxySettingsEndpoint swaggerApiProxySettingsEndPoint = new RESTApiProxySettingsEndpoint("Java", swaggerProxy.getClass().getSimpleName());
            swaggerApiProxySettingsEndPoint.Url = "ttp://catford/Open.Services/";
            RESTApiProxySettingsEndpoint[] endpoints = new RESTApiProxySettingsEndpoint[] { swaggerApiProxySettingsEndPoint };
            RESTServiceDefinition restServiceDefinition = swaggerProxyGenerator.generateSourceString(swaggerApiProxySettingsEndPoint, swaggerDocument, "Admin", "", "");
//            List<RESTServiceDefinition> swaggerServiceDefinitionList = swaggerProxyGenerator.generateSourceStringForRestServiceDefinitionList(swaggerApiProxySettingsEndPoint, swaggerDocument, "Admin", "pass2app", "stage1");
            LOGGER.debug("got swaggerServiceDefinitionList");
        } catch (Exception e) {
            LOGGER.warn("exception generating from Swagger document: " + e.getMessage());
        }
    }
}
