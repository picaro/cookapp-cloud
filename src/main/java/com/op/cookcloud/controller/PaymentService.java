package com.op.cookcloud.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.op.cookcloud.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import com.mkyong.transaction.TransactionBo;
import javax.ws.rs.PathParam;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import org.apache.ws.commons.util.NamespaceContextImpl;

import org.apache.xmlrpc.common.TypeFactory;
import org.apache.xmlrpc.common.TypeFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcController;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.apache.xmlrpc.parser.DateParser;
import org.apache.xmlrpc.parser.TypeParser;
import org.apache.xmlrpc.serializer.DateSerializer;
import org.apache.xmlrpc.serializer.TypeSerializer;
import org.apache.xmlrpc.util.XmlRpcDateTimeDateFormat;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.xml.sax.SAXException;

@Component
@Path("/barcode")
public class PaymentService {

    public static final String UPC_DATABASE_RPC_KEY = "ba88ded7443fb2c270bb2a08e7382d72081cfcc4";

    @GET
    @Path("{code}")
    public Response savePayment(@PathParam("code") String code) {

       // String result = "" + code;//transactionBo.save();
        Product product = new Product();

        Map result = searchUPCdatabase("upc", code);

        System.out.println(result);
        if (result != null && !result.get("status").equals("fail")) {
            //String resultSize = result.get("size").toString();
            String resultDesc = (String)result.get("description");
            String name = "";
            if (resultDesc != null) {
                resultDesc.substring(0,resultDesc.indexOf(' '));
                if (name.length() <= 4) name = resultDesc.substring(0,8);
                product.setName(name);
                product.setDescription(resultDesc );
            }
            product.setCookSeconds(60);
        }


        return Response.status(200).entity(product).build();

    }


    @SuppressWarnings("unchecked")
    private static Map searchUPCdatabase(String code, String codeFormat) {
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
            params.put("rpc_key", UPC_DATABASE_RPC_KEY);
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