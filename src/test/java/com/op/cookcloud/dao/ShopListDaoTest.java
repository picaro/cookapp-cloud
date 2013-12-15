package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.ShopList;
import org.junit.Test;

import java.util.List;

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
    public void testCreateDelete() throws Exception {
        ShopList shopList = createShopList();
        assertTrue(shopListDao.findAll().size() > 0);
        ShopList shopListNew = shopListDao.findById(shopList.getId());
        shopListDao.delete(shopListNew);
        assertTrue(shopListDao.findAll().size() == 0);
    }



    @Test
    public void testUpdate() throws Exception {
        createShopList();
        List shopListL = shopListDao.findByNote("aaa");
        assertTrue(shopListL.size() > 0);

    }

}
