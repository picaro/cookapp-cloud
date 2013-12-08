package com.op.cookcloud;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 11:47 AM
 */
public class AppConstants {

    public static final String UPC_DATABASE_RPC_KEY = "ba88ded7443fb2c270bb2a08e7382d72081cfcc4";

    private static final Logger LOG = Logger.getLogger(AppConstants.class);


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

        LOG.debug("MONGO_LOGIN:" + MONGO_LOGIN);
        LOG.debug("MONGO_PASSWORD:" + MONGO_PASSWORD);
        LOG.debug("MONGO_URL:" + MONGO_URL);
    }
}
