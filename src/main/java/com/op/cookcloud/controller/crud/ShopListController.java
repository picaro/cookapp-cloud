package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.base.ShopList;
import org.apache.log4j.Logger;
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


    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public Response read() {
        ShopList shopList = new ShopList();
        shopList.getProductList().add(null);
        Response response = Response.status(200).entity(shopList).build();
        return response;
    }

    @Override
    public void update(ShopList o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(ShopList o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}