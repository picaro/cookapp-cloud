package com.op.cookcloud.helper;

import com.op.cookcloud.controller.BarcodeController;
import com.op.cookcloud.model.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 12:21 PM
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})

@Ignore(value = "manual run")
public class BarcodeControllerTest {

    ApplicationContext ac;
    @Before
    public void setUp()
    {
        ac = new FileSystemXmlApplicationContext("classpath:/applicationContext-test.xml");
    }

    //@Autowired
    BarcodeController restController;
//
//    //Ignore
    @Test
    public void getProductTest(){
        restController = (BarcodeController) ac.getBean("restController");
        Response response= restController.getProductByCode("0000040102078");    //TODO check in Bsf
        Product product = (Product)response.getEntity();
        assertEquals(product.getName(),"snickers bar");
    }



}
