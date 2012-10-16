package com.op.cookcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

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
    
    //If not found - return error
    if (1 == 1) 
        throw new NotFoundException("Id not found in the request");
    //Source source = new StreamSource(new StringReader(body));
		//Employee e = (Employee) jaxb2Mashaller.unmarshal(source);

    
		return  product;
;

	}
 
 @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id Not Found")
  public class NotFoundException extends Exception {

    public NotFoundException(String msg) {
        super(msg);
    }
  }
	
}