package com.op.cookcloud.helper;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.Product;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.common.TypeFactory;
import org.apache.xmlrpc.common.TypeFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcController;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.apache.xmlrpc.parser.DateParser;
import org.apache.xmlrpc.parser.TypeParser;
import org.apache.xmlrpc.serializer.DateSerializer;
import org.apache.xmlrpc.serializer.TypeSerializer;
import org.xml.sax.SAXException;

import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 11:48 AM
 */
public class UPCDatabaseHelper implements BarcodeSearcher {

    public static final String RPC_KEY = "rpc_key";
    public static final String EAN = "ean";

    @SuppressWarnings("unchecked")
    public static Product lookUpProduct(String code, String codeFormat) {
        try {

            XmlRpcClient client = new XmlRpcClient();

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(AppConstants.HTTP_WWW_UPCDATABASE));
            client.setConfig(config);

            final DateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd?HH:mm:ss");

//            XmlRpcCommonsTransportFactory transportFactory
//                    = new XmlRpcCommonsTransportFactory( client );
//            HttpClient httpClient = new HttpClient();
//            HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
//            hostConfiguration.setProxy( "proxy2.cht", 3128 );
//            hostConfiguration.setHost( AppConstants.HTTP_WWW_UPCDATABASE );
//            transportFactory.setHttpClient( httpClient );
//            client.setTransportFactory( transportFactory );


            TypeFactory typeFactory = getCustomDateTypeFactory(client, format);
            client.setTypeFactory(typeFactory);

            Map<String, String> params = new Hashtable<String, String>();
            params.put(RPC_KEY, AppConstants.UPC_DATABASE_RPC_KEY);
            params.put(code, codeFormat);
            Vector paramsV = new Vector();
            paramsV.addElement(params);

            Map result = (Map) client.execute("lookup", paramsV);

            if (result != null && !result.get("status").equals("fail")) {
                if (((Boolean) result.get("found"))) {
                    Product product = new Product();
                    product.setDescription((String) result.get(AppConstants.DESCRIPTION));
                    product.setName((String) result.get(AppConstants.DESCRIPTION));
                    product.setSize((String) result.get("size"));
                    product.setEan((String) result.get(AppConstants.EAN));
                    product.setUpc((String) result.get(AppConstants.UPC));
                    product.setCountry((String) result.get(AppConstants.COUNTRY));
                    product.setCountryCode((String) result.get("issuerCountryCode"));
                    DateFormat dateFormat = new SimpleDateFormat(AppConstants.DATE_TIME_PATTERN);
                    Calendar cal = Calendar.getInstance();
                    product.setAddDate(dateFormat.format(cal.getTime()));
                    product.setLang(AppConstants.EN);
                    return product;
                }
            }

        } catch (Exception nl) {
            nl.printStackTrace();
        }
        return null;
    }

    private static TypeFactory getCustomDateTypeFactory(
            XmlRpcController pController, final Format pFormat) {
        return new TypeFactoryImpl(pController) {
            private TypeSerializer dateSerializer = new DateSerializer(pFormat);

            public TypeParser getParser(XmlRpcStreamConfig pConfig,
                                        NamespaceContextImpl pContext, String pURI,
                                        String pLocalName) {
                if (DateSerializer.DATE_TAG.equals(pLocalName)) {
                    return new DateParser(pFormat) {
                        @Override
                        protected void setResult(String result) {
                            try {
                                super.setResult("10");    //???
                            } catch (SAXException e) {
                               // e.printStackTrace();
                            }
                        }
                    };
                } else {
                    return super.getParser(pConfig, pContext, pURI, pLocalName);
                }
            }

            public TypeSerializer getSerializer(XmlRpcStreamConfig pConfig,
                                                Object pObject) throws SAXException {
                if (pObject instanceof Date) {
                    return dateSerializer;
                } else {
                    return super.getSerializer(pConfig, pObject);
                }
            }

        };
    }


}
