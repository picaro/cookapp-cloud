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
        ShopList shopList1 = shopListController.read(1);
        LOG.debug(shopList1.getId());
    }




}
