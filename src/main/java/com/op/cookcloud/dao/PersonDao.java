package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.Person;

import java.util.List;

public interface PersonDao extends AbstractDao<Person, String> {
    void saveUser(Person user);
    List<Person> findUsers(String userName);
}
