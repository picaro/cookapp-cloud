package com.op.cookcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.apache.xmlrpc.XmlRpcClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.op.cookcloud.model.Product;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//import javax.xml.transform.Source;
//import javax.xml.transform.stream.StreamSource;


@Controller
@RequestMapping("/barcode")
public class BarcodeController {

//	private Jaxb2Marshaller jaxb2Mashaller;
	
//	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
//		this.jaxb2Mashaller = jaxb2Mashaller;
//	}


	@RequestMapping(value="/{code}", method = RequestMethod.GET,headers="Accept=application/xml, application/json")
	public @ResponseBody Product getMovie(@PathVariable String code, ModelMap model) throws NotFoundException {
      Product product = new Product();
      if (code.equals("1")){
        product.setName("234");
        return product;
      }
    //Try to find product by code from MondoDB    
    //If ok - return Product with seconds
    
    //If not ok - look in barcodes DB return Product name 
    
    //HashMap result = searchUPCdatabase("ean", code);

		//if (result != null) {
		//	String resultSize = result.get("size").toString();
		//	String resultDesc = result.get("description").toString();
		//itemsFound.add(new Item(resultDesc, itemProductData, itemDataFormat));

	//	}
    
    //If not found - return error
    if (1 == 1) 
        throw new NotFoundException("Id not found in the request");
    //Source source = new StreamSource(new StringReader(body));
		//Employee e = (Employee) jaxb2Mashaller.unmarshal(source);

    
		return  product;

	}
 
 	public static final String NAME_SEARCH = "Name Search";
	public static final String BARCODE_SEARCH = "Barcode Search";
	public static final String PRODUCTID_SEARCH = "ProductId Search";
	public static final String UPC_DATABASE_RPC_KEY = "ba88ded7443fb2c270bb2a08e7382d72081cfcc4";
 
 	@SuppressWarnings("unchecked")
	private static HashMap searchUPCdatabase(String code, String codeFormat) {
		try {
			XmlRpcClient client = new XmlRpcClient("http://www.upcdatabase.com/xmlrpc");
			Map<String, String> params = new HashMap<String, String>();
			params.put("rpc_key", UPC_DATABASE_RPC_KEY);
			params.put(codeFormat, code);
     Vector paramsV = new Vector();
     paramsV.addElement(UPC_DATABASE_RPC_KEY);
     paramsV.addElement(code);
			return (HashMap) client.execute("lookup", paramsV);
			//return (HashMap) client.call("test", params);
		} catch (Exception nl) {
			nl.printStackTrace();
		} 
		return null;
	}
 
 
 
 @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id Not Found")
  public class NotFoundException extends Exception {

    public NotFoundException(String msg) {
        super(msg);
    }
  }
	
}