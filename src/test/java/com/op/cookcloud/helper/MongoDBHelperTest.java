package com.op.cookcloud.helper;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.Comment;
import com.op.cookcloud.model.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 07.11.13
 * Time: 1:42
 * To change this template use File | Settings | File Templates.
 */
public class MongoDBHelperTest {

    public static final String EAN = "111119";
    MongoDBHelper mongoDBHelper = new MongoDBHelper();

    @Test
    public void checkAddDelProduct(){
        Product product = new Product();
        product.setEan(EAN);
        product.setName("proname");
        int countStart = mongoDBHelper.count(product);

        mongoDBHelper.saveProduct(product);
        int countEnd = mongoDBHelper.count(product);
//        assertTrue(countStart == countEnd - 1);

        Product pro = mongoDBHelper.getProductByEAN(EAN, AppConstants.EN);
        System.out.println("saved prod:" + pro.getName());


        mongoDBHelper.delProduct(product, AppConstants.EN);
        countEnd = mongoDBHelper.count(product);
        assertTrue(countStart == countEnd);
    }

    @Test
    public void addProductComment(){
        Product product = new Product();
        product.setEan(EAN);
        product.setName("proname");
        mongoDBHelper.saveProduct(product);

        List comments = new ArrayList();
        Comment comment = new Comment("aaa", "aa@aaa.aa");
        comments.add(comment);
        mongoDBHelper.addCommentEAN(EAN,comment, AppConstants.EN);


        Product product2 = mongoDBHelper.getProductByEAN(EAN, AppConstants.EN);
        assertEquals(product2.getCommentList().size() , 1);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setEan(EAN);
        product.setName("proname");
        mongoDBHelper.saveProduct(product);

        Product product2 = mongoDBHelper.getProductByEAN(EAN, AppConstants.EN);
        product2.setName("eeee");
        mongoDBHelper.updateProductEAN(EAN, product2);

        Product product3 = mongoDBHelper.getProductByEAN(EAN, AppConstants.EN);
        assertEquals("eeee",product3.getName());
    }
}