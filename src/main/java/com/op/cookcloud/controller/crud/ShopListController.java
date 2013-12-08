package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.ShopList;
import com.sun.jersey.api.core.InjectParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Service("shopListService")
@Transactional(readOnly = true)
@Path("/shoplist")
public class ShopListController implements CRUDController<ShopList>{

    private static final Logger LOG = Logger.getLogger(ShopListController.class);


    @InjectParam
    private ShopListDao shopListDao;

    public ShopListDao getShopListDao() {
        return shopListDao;
    }

    public void setShopListDao(ShopListDao shopListDao) {
        this.shopListDao = shopListDao;
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public Response read() {
        ShopList shopList = shopListDao.findById(new Integer(1));
        Response response = Response.status(200).entity(shopList).build();
        return response;
    }

    @Override
    public void update(ShopList o) {
        shopListDao.save(o);
    }

    @Override
    public void create(ShopList o) {
        shopListDao.save(o);
    }

    @Override
    public void delete(Integer id) {
        ShopList shopList = new ShopList();
        shopList.setId(id);
        shopListDao.delete(shopList);
    }


}