package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.Product;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 15.12.13
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class ProductDaoTest extends BaseDaoTest {

    @Test
    public void testRead() {
        Product product = createProduct();
        assertNotNull(productDao);
        Product testProduct = productDao.findById(1);
        assertNotNull(testProduct);
        assertEquals(1, testProduct.getId());
        assertNotNull(testProduct.getShoplist());
        assertEquals("лезвия", testProduct.getName());
        productDao.delete(product);
    }


    @Test
    public void testCreate() {
        Product product = createProduct();
        assertNotNull(product);
        assertNotNull(productDao);
        Product testProduct = productDao.findById(1);
        assertNotNull(testProduct);
        assertEquals(1, product.getId());
        assertNotNull(product.getShoplist());
        assertEquals("лезвия", product.getName());
        productDao.delete(product);
    }

    @Test
    public void testDelete() {
        Product product = createProduct();
        assertNotNull(product);
        assertNotNull(personDao);
        productDao.delete(product);
        Product testProduct = productDao.findById(1);
        assertNull(testProduct);
    }

    @Test
    public void testUpdate() {
        Product product = createProduct();
        assertNotNull(personDao);
        product.setName("Бритва харьков");
        productDao.saveOrUpdate(product);
        Product testProduct = productDao.findById(1);
        assertNotNull(testProduct);
        assertEquals("Бритва харьков", testProduct.getName());
        productDao.delete(product);
    }
}
