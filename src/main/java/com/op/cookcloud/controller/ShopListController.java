package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.Shop;
import com.op.cookcloud.model.base.ShopList;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Service("shopListService")
@Transactional
@Path("/shoplist")
@RequestMapping("/shopList")
@Log4j
public class ShopListController {

    @Autowired
    private ShopListDao shopListDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @RequestMapping( value = "/allShops")
    public ModelAndView readAll() {
        List<ShopList> shopList = shopListDao.findAll();
        return new ModelAndView("shops", "shopList", shopList);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public ShopList read(@PathParam("id") Integer id) {
        ShopList shopList = shopListDao.findById(id);
        if (log.isDebugEnabled()) {
            log.debug("ShopList found, id: " + id);
        }
        return shopList;
    }

    @PUT
    public void update(@ModelAttribute ShopList shopList) {
        shopListDao.saveOrUpdate(shopList);
        if (log.isDebugEnabled()) {
            log.debug("ShopList updated" + shopList);
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute ShopList shopList) {
        shopListDao.saveOrUpdate(shopList);
        log.debug("ShopList created: " + shopList);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        ShopList shop = shopListDao.findById(id);
        shopListDao.delete(shop);
        log.debug("ShopList deleted:" + shop);
    }

}