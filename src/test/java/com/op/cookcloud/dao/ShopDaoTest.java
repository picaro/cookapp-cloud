package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.Shop;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.12.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */

public class ShopDaoTest extends BaseDaoTest {

    @Test
    public void testRead() {
        Shop shop = createShop();
        Shop currentShop = shopDao.findAll().get(0);
        assertNotNull(currentShop);
        assertEquals("testShop", currentShop.getName());
        shopDao.delete(shop);
    }

    @Test
    public void testCreateDelete() throws Exception {
        Shop shop = createShop();
        assertTrue(shopDao.findAll().size() > 0);
        Shop shopNew = shopDao.findById(shop.getId());
        shopDao.delete(shopNew);
        assertTrue(shopListDao.findAll().size() == 0);
    }

    public void testUpdate() {
        Shop shop = createShop();
        shop.setId(2);
        shop.setName("testShop2");
        shopDao.saveOrUpdate(shop);
        Shop testShop = shopDao.findById(2);
        assertEquals(2, testShop.getId());
        assertEquals("testShop2", testShop.getName());
        shopDao.delete(testShop);

    }

}
