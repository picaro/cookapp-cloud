package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.helper.*;
import com.op.cookcloud.model.Comment;
import com.op.cookcloud.model.Product;
import com.op.cookcloud.model.base.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


@Service("personService")
@Transactional(readOnly = true)
@Path("/person")
public class PersonController implements CRUDController<Person>{

    private static final Logger LOG = Logger.getLogger(PersonController.class);


    @Override
    public Response read() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Person o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Person o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Integer id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}