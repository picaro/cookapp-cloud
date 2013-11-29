package com.op.cookcloud.controller.crud;

import com.op.cookcloud.model.base.Circle;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Service("circleService")
@Transactional(readOnly = true)
@Path("/circle")
public class CircleController implements CRUDController<Circle>{

    private static final Logger LOG = Logger.getLogger(CircleController.class);


    @Override
    public Response read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Circle o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Circle o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}