package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.ShopList;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.12.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */

public class ShopListDaoTest extends BaseDaoTest{

     @Test
    public void testRead() {
        ShopList shopList = createShopList();
        List<ShopList> testShopList = shopListDao.findByNote("test");
        assertNotNull(testShopList);
        assertTrue(testShopList.size() > 0);
        ShopList newShop = testShopList.get(0);
        assertNotNull(newShop);
        shopListDao.delete(shopList);

    }

    @Test
    public void testCreateDelete() throws Exception {
        ShopList shopList = createShopList();
        assertTrue(shopListDao.findAll().size() > 0);
        ShopList shopListNew = shopListDao.findById(shopList.getId());
        shopListDao.delete(shopListNew);
        assertTrue(shopListDao.findAll().size() == 0);
    }

    @Test
    public void shouldReturnShopListsForCurrentUser() throws Exception {
        ShopList shopList = createShopList();
        assertTrue(shopListDao.findAll().size() > 0);
        List<ShopList> shopLists = shopListDao.findByPerson("TestEmail@gmail.com");
        //shopListDao.delete(shopListNew);
        assertTrue(shopLists.size() > 0);
    }


    @Test
    public void testUpdate() throws Exception {
        ShopList shopList = createShopList();
        List<ShopList> testShopList = shopListDao.findByNote("test");
        assertTrue(testShopList.size() > 0);
        ShopList newShop = testShopList.get(0);
        assertNotNull(newShop);
        shopListDao.saveOrUpdate(newShop);
        shopListDao.delete(shopList);

    }

}
