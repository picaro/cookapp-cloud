package com.op.cookcloud.helper;

import com.op.cookcloud.AppConstants;
import org.apache.ws.commons.util.NamespaceContextImpl;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
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
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * Copyright  2013 EngagePoint. All rights reserved.
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 11:48 AM
 */
public class UPCDatabaseHelper {

    @SuppressWarnings("unchecked")
    public static Map searchUPCdatabase(String code, String codeFormat) {
        try {

            XmlRpcClient client = new XmlRpcClient();

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://www.upcdatabase.com/xmlrpc"));
            client.setConfig(config);

            final DateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd?HH:mm:ss");
            TypeFactory typeFactory = getCustomDateTypeFactory(client, format);
            client.setTypeFactory(typeFactory);

            Map<String, String> params = new Hashtable<String, String>();
            params.put("rpc_key", AppConstants.UPC_DATABASE_RPC_KEY);
            params.put("ean", codeFormat);
            Vector paramsV = new Vector();
            paramsV.addElement(params);
            return (Map) client.execute("lookup", paramsV);
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
                                super.setResult("10");
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
