package com.op.cookcloud.helper;

import com.op.cookcloud.controller.BarcodeController;
import com.op.cookcloud.model.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 12:21 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
@Ignore(value = "mongodb ?")
public class BarcodeControllerIT {

    @Autowired
    ApplicationContext ac;

    @Autowired
    BarcodeController restController;

    @Test
    public void getProductTest() throws IOException {
        Response response= restController.getProductByCode("0000040102078");    //TODO check in Bsf
        Product product = (Product)response.getEntity();
        assertEquals(product.getName(),"snickers bar");
    }



}
