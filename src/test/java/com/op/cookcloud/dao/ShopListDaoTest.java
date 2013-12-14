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
    public void testRead() throws Exception {
        Person person = createPerson();
        ShopList shopList = new ShopList();
        shopList.setNote("aaa");
        shopList.setPerson(person);
        shopListDao.save(shopList);

        assertTrue(shopListDao.findAll().size() > 0);

        ShopList shopListNew = shopListDao.findById(1);
        shopListDao.delete(shopListNew);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}
