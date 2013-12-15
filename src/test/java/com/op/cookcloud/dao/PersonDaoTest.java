package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.Person;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 15.12.13
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
public class PersonDaoTest extends BaseDaoTest {
    private Person person;

    @Before
    public void init() {
        person = new Person();
        person.setFirstName("Alex");
        person.setLastName("Ivanov");
        person.setEmail("TestEmail@gmail.com");
        person.setGender("M");
        person.setId(1);
    }

    @Test
    public void testRead() {
        personDao.saveUser(person);
        Person testPerson = personDao.findById(1);
        assertNotNull(testPerson);
        assertEquals("Alex", testPerson.getFirstName());
        assertEquals("Ivanov", testPerson.getLastName());
        assertEquals("TestEmail@gmail.com", testPerson.getEmail());
        assertEquals("M", testPerson.getGender());
        assertEquals(1, testPerson.getId());
        personDao.delete(person);

    }

    @Test
    public void testCreate() {
        personDao.saveUser(person);
        Person testPerson = personDao.findById(1);
        assertNotNull(testPerson);
        assertEquals("Alex", testPerson.getFirstName());
        assertEquals("Ivanov", testPerson.getLastName());
        assertEquals("TestEmail@gmail.com", testPerson.getEmail());
        assertEquals("M", person.getGender());
        personDao.delete(person);
    }

    @Test
    public void testDelete() {
        personDao.saveUser(person);
        personDao.delete(person);
        Person testPerson = personDao.findById(1);
        assertNull(testPerson);
    }

    @Test
    public void testUpdate() {
        personDao.saveUser(person);
        person.setFirstName("Alex_test");
        person.setLastName("Ivanov_test");
        personDao.saveOrUpdate(person);
        Person testPerson = personDao.findById(1);
        assertNotNull(testPerson);
        assertEquals("Alex_test", testPerson.getFirstName());
        assertEquals("Ivanov_test", testPerson.getLastName());
        personDao.delete(person);
    }

}
