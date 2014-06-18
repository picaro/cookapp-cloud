package com.op.cookcloud;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

/**
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 11:47 AM
 */
@Service
@Log4j
public class AppConstants {

    public static final String UPC_DATABASE_RPC_KEY = "ba88ded7443fb2c270bb2a08e7382d72081cfcc4";

    private static final Properties properties = new Properties();
    public static final String CHARSET_UTF_8 = "; charset=UTF-8";
    private static transient boolean isparamsDone = false;

    public static final String EN = "EN";

    public static final String HTTP_WWW_UPCDATABASE = "http://www.upcdatabase.com/xmlrpc";
    public static final String DBNAME = "cookclouddb";
    public static final String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final String NAME = "name";
    public static final String EAN = "ean";
    public static final String UPC = "upc";

    public static final String DESCRIPTION = "description";
    public static final String ADDDATE = "adddate";
    public static final String COUNTRY = "country";
    public static final String COMMENTS = "comments";
    public static final String SIZE = "size";
    public static final String ISSUER_COUNTRY_CODE = "issuerCountryCode";

    public static final String UNAUTHORIZED = "Unauthorized";
    
    public static final String SUCCESS = "success";
    
    public static final String FAILURE = "failure";
    
    public static final String SESSION_ID = "sessionId";
    
    public static final String ERROR_CODE = "errorCode";

    public static final String MESSAGE = "message";

    public static String MONGO_LOGIN;
    public static String MONGO_PASSWORD;
    public static String MONGO_URL;

    public AppConstants() throws IOException {

        if (!isparamsDone) {
            synchronized (this) {
                initParams();
                isparamsDone = true;
            }
        }

    }

    private void initParams() throws IOException {
        properties.load(this.getClass().getResourceAsStream("/config.properties"));
        MONGO_LOGIN = properties.getProperty("MONGO_LOGIN");
        MONGO_PASSWORD = properties.getProperty("MONGO_PASSWORD");
        MONGO_URL = properties.getProperty("MONGO_URL");

        log.debug("MONGO_LOGIN:" + MONGO_LOGIN);
        log.error("MONGO_PASSWORD:" + MONGO_PASSWORD);
        log.debug("MONGO_URL:" + MONGO_URL);
    }
}
