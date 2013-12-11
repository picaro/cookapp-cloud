package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ShopDao;
import com.op.cookcloud.model.base.Shop;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Service("shopService")
@Transactional(readOnly = true)
@Path("/shop")
public class ShopController {

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Autowired
    private ShopDao shopDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Shop read(@PathParam("id") Integer id) {
        Shop person = shopDao.findById(id);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Shop found, id: " + id);
        }
        return person;
    }

    @PUT
    public void update(@ModelAttribute Shop shop) {
        shopDao.saveOrUpdate(shop);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Shop updated" + shop);
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Shop shop) {
        shopDao.saveOrUpdate(shop);
        LOG.debug("Person created: " + shop);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        Shop shop = shopDao.findById(id);
        shopDao.delete(shop);
        LOG.debug("Product deleted:" + shop);
    }


}