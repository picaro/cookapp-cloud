package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.model.base.Person;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Service("personService")
@Transactional
@RequestMapping("/person")
@Log4j
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @RequestMapping(value = "/allPersons")
    public ModelAndView readAll() {
        List<Person> personList = personDao.findAll();
        return new ModelAndView("persons", "personList",personList);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Person read(@PathParam("id") Integer id) {
        Person person = personDao.findById(id);
        if (log.isDebugEnabled()) {
            log.debug("Person found, id: " + id);
        }
        return person;
    }

    @PUT
    public void update(@ModelAttribute Person person) {
        personDao.saveOrUpdate(person);
        if (log.isDebugEnabled()) {
            log.debug("Person updated" + person);
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Person person) {
        personDao.saveOrUpdate(person);
        log.debug("Person created: " + person);
    }

    @DELETE
    @Path("{id}")
    @RequestMapping(value = "/delete")
    public ModelAndView  delete(@RequestParam(value = "id", required = true) Integer id) {
        Person person = personDao.findById(id);
        log.debug("Received request to delete person:" + person);
        personDao.delete(person);
        log.debug("Person deleted: " + person);
        return new ModelAndView("admin");
    }
}