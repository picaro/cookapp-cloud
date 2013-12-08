package com.op.cookcloud.controller.crud;

import com.op.cookcloud.dao.impl.ShopListDao;
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

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.12.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
@TransactionConfiguration
@Transactional
public class ShopListControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ShopListDao shopListDao;


    @Test
    public void testRead() throws Exception {
//        ShopList shopList = new ShopList();
//        shopList.setNote("aaa");
//        shopList.setNote("aaa");
//        shopListDao.save(shopList);
//
//        ShopList shopListNew = shopListDao.findById(1);
        //shopListDao.r delete(1);
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
