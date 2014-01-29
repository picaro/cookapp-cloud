package com.op.cookcloud.controller;

import com.op.cookcloud.dao.BaseDaoTest;
import com.op.cookcloud.model.base.Product;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 15.12.13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class ProductControllerTest extends BaseDaoTest {

    private static final Logger LOG = Logger.getLogger(ProductControllerTest.class);


    @Autowired
    private ProductController productController;

    private Product product;

    @Before
    public void before() {
        Product product = new Product();
//        product.setShoplist(createShopList());
//        product.setName("aaa");
        productController.create(product);
    }

    @Test
    public void getProductsTest() {
//        List<Product> productL = productDao.findAll();
//        Product product1 = productController.read(productL.get(0).getId());
//       assertEquals(product1.getName(), "aaa");
//        LOG.debug(product1.getId());
    }

    @Test
    public void deleteProductsTest() {
        List<Product> productL = productDao.findAll();
        assertEquals(productL.size(), 1);

        productController.delete(productL.get(0).getId());

        assertEquals(productDao.findAll().size(), 0);
    }

    @Test
    public void updateProductsTest() {
//        List<Product> productL = productDao.findAll();
//        assertEquals(productL.size(),1);
//
//        Product product1 = productL.get(0);
//        product1.setName("new note");
//        productController.update(product1);


//        productL = productDao.findAll();
//        assertEquals(productL.get(0).getName(),"new note");
    }


}
