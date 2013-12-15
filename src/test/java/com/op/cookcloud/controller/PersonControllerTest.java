package com.op.cookcloud.controller;

import com.op.cookcloud.dao.BaseDaoTest;
import com.op.cookcloud.model.base.Person;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 15.12.13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class PersonControllerTest extends BaseDaoTest{

    private static final Logger LOG = Logger.getLogger(PersonControllerTest.class);


    @Autowired
    private PersonController personController;

    private Person person;

    @Before
    public void before(){
        Person person = new Person();
        person.setFirstName("aaa");
        personController.create(person);
    }

    @Test
    public void getPersonsTest(){
        List<Person> personL = personDao.findAll();

        Person person1 = personController.read(personL.get(0).getId());
        assertEquals(person1.getFirstName(), "aaa");
        LOG.debug(person1.getId());
    }

    @Test
    public void deletePersonsTest(){
        List<Person> personL = personDao.findAll();
        assertEquals(personL.size(),1);

        personController.delete(personL.get(0).getId());

        assertEquals(personDao.findAll().size(), 0);
    }

    @Test
    public void updatePersonsTest(){
        List<Person> personL = personDao.findAll();
        assertEquals(personL.size(),1);

        Person person1 = personL.get(0);
        person1.setFirstName("new note");
        personController.update(person1);


        personL = personDao.findAll();
        assertEquals(personL.get(0).getFirstName(),"new note");
    }




}
