package com.op.cookcloud.controller;

import com.op.cookcloud.helper.EANdirectoryRuHelper;
import com.op.cookcloud.model.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
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
public class RestControllerTest {

    ApplicationContext ac;
    @Before
    public void setUp()
    {
        ac = new FileSystemXmlApplicationContext("classpath:/applicationContext-test.xml");
    }

    //@Autowired
    RestController restController;
//
//    //Ignore
    @Test
    public void getProductTest(){
        restController = (RestController) ac.getBean("restController");
        Response response= restController.getProductByCode("0000040102078");    //TODO check in Bsf
        Product product = (Product)response.getEntity();
        assertEquals(product.getName(),"snickers bar");
    }



}
