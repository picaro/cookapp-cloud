package com.op.cookcloud.helper;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.Comment;
import com.op.cookcloud.model.Product;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
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

    public static final String EAN = "111121";
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


        mongoDBHelper.delProduct(product);
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

    //@Ignore
    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setEan(EAN);
        product.setName("proname");
        mongoDBHelper.saveProduct(product);

        Product product2 = mongoDBHelper.getProductByEAN(EAN, AppConstants.EN);
        //mongoDBHelper.delProduct(product,"EN");
        product2.setName("eeee");


        Comment comment = new Comment();
        comment.setComment("aaa");
        product2.getCommentList().add(comment);
        //mongoDBHelper.saveProduct(product2);
        mongoDBHelper.updateProduct(product2);

        Product product3 = mongoDBHelper.getProductByEAN(EAN, AppConstants.EN);
        assertEquals("eeee",product3.getName());
        assertEquals("aaa",product3.getCommentList().get(0).getComment());
        mongoDBHelper.delProduct(product3);
    }


    @Test
    public void checkSettings() throws IOException {
        mongoDBHelper.isUADBSaved();
        //mongoDBHelper.setUADBSaved();
        //mongoDBHelper.isUADBSaved();

        //assertEquals(product2.getCommentList().size() , 1);
    }
}