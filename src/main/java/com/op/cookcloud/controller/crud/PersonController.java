package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.model.base.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Service("personService")
@Transactional(readOnly = true)
@Path("/person")
public class PersonController implements CRUDController<Person> {

    private static final Logger LOG = Logger.getLogger(PersonController.class);

    @Autowired
    private PersonDao personDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Person read(@PathParam("id") Integer id) {
        Person person = personDao.findById(id);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Person found, id: " + id);
        }
        return person;
    }

    @PUT
    public void update(@ModelAttribute Person person) {
        personDao.saveOrUpdate(person);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Person updated" + person);
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Person person) {
        personDao.saveOrUpdate(person);
        LOG.debug("Person created: " + person);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        LOG.debug("Person deleted: " + person);
    }
}