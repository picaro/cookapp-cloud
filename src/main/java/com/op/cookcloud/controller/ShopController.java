package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ShopDao;
import com.op.cookcloud.model.base.Product;
import com.op.cookcloud.model.base.Shop;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Service("shopService")
@Transactional
@RequestMapping("/shop")
@Log4j
public class ShopController {
    @Autowired
    private ShopDao shopDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @RequestMapping("/allShops")
    public ModelAndView readAll() {
        List<Shop> shopList = shopDao.findAll();
        return new ModelAndView("shop", "shopList", shopList);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Shop read(@PathParam("id") Integer id) {
        Shop person = shopDao.findById(id);
        if (log.isDebugEnabled()) {
            log.debug("Shop found, id: " + id);
        }
        return person;
    }

    @PUT
    public void update(@ModelAttribute Shop shop) {
        shopDao.saveOrUpdate(shop);
        if (log.isDebugEnabled()) {
            log.debug("Shop updated" + shop);
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Shop shop) {
        shopDao.saveOrUpdate(shop);
        log.debug("Person created: " + shop);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        Shop shop = shopDao.findById(id);
        shopDao.delete(shop);
        log.debug("Product deleted:" + shop);
    }


}