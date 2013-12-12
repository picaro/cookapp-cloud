package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.Circle;
import com.op.cookcloud.model.base.Shop;
import com.op.cookcloud.model.base.ShopList;
import com.sun.jersey.api.core.InjectParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Service("shopListService")
@Transactional(readOnly = true)
@Path("/shoplist")
public class ShopListController implements CRUDController<ShopList>{

    private static final Logger LOG = Logger.getLogger(ShopListController.class);

    @Autowired
    private ShopListDao shopListDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public ShopList read(@PathParam("id") Integer id) {
        ShopList shopList = shopListDao.findById(id);
        if (LOG.isDebugEnabled()) {
            LOG.debug("ShopList found, id: " + id);
        }
        return shopList;
    }

    @PUT
    public void update(@ModelAttribute ShopList shopList) {
        shopListDao.saveOrUpdate(shopList);
        if (LOG.isDebugEnabled()) {
            LOG.debug("ShopList updated" + shopList);
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute ShopList shopList) {
        shopListDao.saveOrUpdate(shopList);
        LOG.debug("ShopList created: " + shopList);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        ShopList shop = shopListDao.findById(id);
        shopListDao.delete(shop);
        LOG.debug("ShopList deleted:" + shop);
    }

}