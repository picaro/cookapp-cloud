package com.op.cookcloud.controller.crud;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;


@Service("shopListService")
@Transactional(readOnly = true)
@Path("/shoplist")
public class ShopListController implements CRUDController{

    private static final Logger LOG = Logger.getLogger(ShopListController.class);


    @Override
    public Object read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}