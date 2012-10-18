package com.op.cookcloud.controller;

//import org.apache.xmlrpc.XmlRpcClientLite;
//import org.apache.xmlrpc.XmlRpcClientRequest;
//import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.xml.sax.SAXException;

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

import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;

import com.op.cookcloud.model.Product;

//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//import javax.xml.transform.Source;
//import javax.xml.transform.stream.StreamSource;

@Controller
@RequestMapping("/barcode")
public class BarcodeController {

	//private Jaxb2Marshaller jaxb2Mashaller;
	public static final String UPC_DATABASE_RPC_KEY = "ba88ded7443fb2c270bb2a08e7382d72081cfcc4";

	// public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
	// this.jaxb2Mashaller = jaxb2Mashaller;
	// }

	@RequestMapping(value = "/{code}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody
	Product getProductByCode(@PathVariable String code, ModelMap model)
			throws NotFoundException {
		Product product = new Product();

		if (code.equals("1")) {
			product.setName("234");
			return product;
		}
		// Try to find product by code from MondoDB
		// If ok - return Product with seconds

		// If not ok - look in barcodes DB return Product name

		Map result = searchUPCdatabase("upc", code);

		System.out.println(result);
		if (result != null && !result.get("status").equals("fail")) {
			String resultSize = result.get("size").toString();
			String resultDesc = result.get("description").toString();
			product.setName(resultDesc.substring(0,8));
			product.setDescription(resultDesc );
			return product;
		}

		// If not found - return error
		throw new NotFoundException("Id not found in the request");
		// Source source = new StreamSource(new StringReader(body));
		// Employee e = (Employee) jaxb2Mashaller.unmarshal(source);

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

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id Not Found")
	public class NotFoundException extends Exception {

		public NotFoundException(String msg) {
			super(msg);
		}
	}

}