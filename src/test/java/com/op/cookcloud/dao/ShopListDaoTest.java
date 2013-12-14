package com.op.cookcloud.dao;

import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.ShopList;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
        assertTrue(shopListDao.findAll().size() > 0);
    }



    @Test
    public void testUpdate() throws Exception {
        createShopList();
        List shopListL = shopListDao.findByNote("aaa");
        assertTrue(shopListL.size() > 0);

    }

}
