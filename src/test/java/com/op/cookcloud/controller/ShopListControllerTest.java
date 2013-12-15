package com.op.cookcloud.controller;

import com.op.cookcloud.dao.BaseDaoTest;
import com.op.cookcloud.model.base.ShopList;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 15.12.13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class ShopListControllerTest extends BaseDaoTest{

    private static final Logger LOG = Logger.getLogger(ShopListControllerTest.class);


    @Autowired
    private ShopListController shopListController;

    @Test
    public void getShopListsTest(){
        ShopList shopList = new ShopList();
        shopList.setNote("aaa");
        shopList.setPerson(createPerson());
        shopListController.create(shopList);

        List<ShopList> shopListL = shopListDao.findAll();
        assertEquals(shopListL.size(),1);

        ShopList shopList1 = shopListL.get(0);
        assertEquals(shopList1.getNote(), "aaa");
        LOG.debug(shopList1.getId());
    }

    @Test
    public void deleteShopListsTest(){
        ShopList shopList = new ShopList();
        shopList.setNote("aaa");
        shopList.setPerson(createPerson());
        shopListController.create(shopList);

        List<ShopList> shopListL = shopListDao.findAll();
        assertEquals(shopListL.size(),1);

        shopListController.delete(shopListL.get(0).getId());

        assertEquals(shopListDao.findAll().size(), 0);
    }

    @Test
    public void updateShopListsTest(){
        ShopList shopList = new ShopList();
        shopList.setNote("aaa");
        shopList.setPerson(createPerson());
        shopListController.create(shopList);

        List<ShopList> shopListL = shopListDao.findAll();
        assertEquals(shopListL.size(),1);

        ShopList shopList1 = shopListL.get(0);
        shopList1.setNote("new note");
        shopListController.update(shopList1);


        shopListL = shopListDao.findAll();
        assertEquals(shopListL.get(0).getNote(),"new note");
    }




}
