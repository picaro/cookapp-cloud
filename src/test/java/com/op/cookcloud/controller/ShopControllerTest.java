package com.op.cookcloud.controller;

import com.op.cookcloud.dao.BaseDaoTest;
import com.op.cookcloud.model.base.Shop;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 15.12.13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class ShopControllerTest extends BaseDaoTest{

    private static final Logger LOG = Logger.getLogger(ShopControllerTest.class);


    @Autowired
    private ShopController shopController;

    @Test
    public void getShopsTest(){
        Shop shop = new Shop();
        shop.setName("aaa");
        shopController.create(shop);

//        Shop shop1 = shopController.read(shopController.readAll().get(0).getId() );
//        assertEquals(shop1.getName(), "aaa");
//        log.debug(shop1.getId());
    }

    @Test
    public void deleteShopsTest(){
        Shop shop = new Shop();
        shop.setName("aaa");
        shopController.create(shop);

        List<Shop> shopL = shopDao.findAll();
        assertEquals(shopL.size(),1);

        shopController.delete(shopL.get(0).getId());

        assertEquals(shopDao.findAll().size(), 0);
    }

    @Test
    public void updateShopsTest(){
        Shop shop = new Shop();
        shop.setName("aaa");
        shopController.create(shop);

        List<Shop> shopL = shopDao.findAll();
        assertEquals(shopL.size(),1);

        Shop shop1 = shopL.get(0);
        shop1.setName("new note");
        shopController.update(shop1);


        shopL = shopDao.findAll();
        assertEquals(shopL.get(0).getName(),"new note");
    }




}
