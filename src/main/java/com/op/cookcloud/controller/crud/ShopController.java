package com.op.cookcloud.controller.crud;

import com.op.cookcloud.model.base.Shop;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Service("shopService")
@Transactional(readOnly = true)
@Path("/shop")
public class ShopController implements CRUDController<Shop> {

    private static final Logger LOG = Logger.getLogger(ShopController.class);


    @Override
    public Response read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Shop o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Shop o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}