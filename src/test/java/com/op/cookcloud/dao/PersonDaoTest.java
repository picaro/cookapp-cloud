package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.Person;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 15.12.13
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
public class PersonDaoTest extends BaseDaoTest {

    @Test
    public void testRead() {
        createPerson();
        assertNotNull(personDao);
        Person person = personDao.findById(1);
        assertNotNull(person);
        assertEquals("Alex", person.getFirstName());
        assertEquals("Ivanov", person.getLastName());
        assertEquals("TestEmail@gmail.com", person.getEmail());
        assertEquals("M", person.getGender());
        assertEquals(1, person.getId());

    }

    @Test
    public void testCreate() {
        Person person = createPerson();
        assertNotNull(person);
        assertNotNull(personDao);
        personDao.saveOrUpdate(person);
        Person testPerson = personDao.findById(2);
        assertNotNull(testPerson);
        assertEquals("Alex", testPerson.getFirstName());
        assertEquals("Ivanov", testPerson.getLastName());
        assertEquals("TestEmail@gmail.com", testPerson.getEmail());
        assertEquals("M", person.getGender());

    }

    @Test
    public void testDelete(){
        Person testPerson = createPerson();
        assertNotNull(testPerson);
        assertNotNull(personDao);
        personDao.delete(testPerson);
        Person person = personDao.findById(3);
        assertNull(person);


    }

    @Test
    public void testUpdate() {
        Person testPerson = createPerson();
        assertNotNull(personDao);
        testPerson.setFirstName("Alex_test");
        testPerson.setLastName("Ivanov_test");
        personDao.saveOrUpdate(testPerson);
        Person person = personDao.findById(4);
        assertNotNull(person);
        assertEquals("Alex_test", person.getFirstName());
        assertEquals("Ivanov_test", person.getLastName());
    }



}
