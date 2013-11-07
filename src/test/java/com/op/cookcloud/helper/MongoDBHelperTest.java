package com.op.cookcloud.helper;

import com.op.cookcloud.model.Product;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 07.11.13
 * Time: 1:42
 * To change this template use File | Settings | File Templates.
 */
public class MongoDBHelperTest {

    MongoDBHelper mongoDBHelper = new MongoDBHelper();

    @Test
    public void checkAddDelProduct(){
        Product product = new Product();
        product.setBarcode("11111");
        product.setName("proname");
        int countStart = mongoDBHelper.count(product);
        mongoDBHelper.saveProduct(product);
        int countEnd = mongoDBHelper.count(product);
        assertTrue(countStart == countEnd - 1);

        Product pro = mongoDBHelper.getProduct("11111");
        System.out.println("saved prod:" + pro.getName());


        mongoDBHelper.delProduct(product);
        countEnd = mongoDBHelper.count(product);
        assertTrue(countStart == countEnd);
    }

    @Test
    public void addProductComment(){

    }

    @Test
    public void updateProduct(){

    }
}