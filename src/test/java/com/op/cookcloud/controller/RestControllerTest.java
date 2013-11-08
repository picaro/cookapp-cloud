package com.op.cookcloud.controller;

import com.op.cookcloud.model.Product;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertTrue;

/**
 * User: alexander.pastukhov
 * Date: 11/6/13
 * Time: 12:21 PM
 */
public class RestControllerTest {

    //1 test GetProduct / With saving

    //Ignore
    @Test
    public void getProductTest(){
          RestController restController = new RestController();
        Response response= restController.getProductByCode("0000040102078");    //TODO check in Bsf
        Product product = (Product)response.getEntity();
          assertTrue(product.getName().equals("snickers"));
    }

    //
    @Test
    public void addCommentTest(){

    }

    //
    @Test
    public void setPriceTest(){

    }

}
