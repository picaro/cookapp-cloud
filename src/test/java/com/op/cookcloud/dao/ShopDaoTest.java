package com.op.cookcloud.dao;

import com.op.cookcloud.dao.impl.ShopDao;
import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.Shop;
import com.op.cookcloud.model.base.ShopList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.12.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */

public class ShopDaoTest extends BaseDaoTest{


    @Test
    public void testCreateDelete() throws Exception {
        Shop shop = createShop();
        assertTrue(shopDao.findAll().size() > 0);
        Shop shopNew = shopDao.findById(shop.getId());
        shopDao.delete(shopNew);
        assertTrue(shopListDao.findAll().size() == 0);
    }

}
