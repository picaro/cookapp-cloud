package com.op.cookcloud.dao.impl;

import com.op.cookcloud.dao.PersonDao;
import com.op.cookcloud.model.base.Person;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao extends AbstractDaoImpl<Person, String> {

    protected PersonDao() {
        super(Person.class);
    }

    @Override
    public void saveUser(Person user) {
        saveOrUpdate(user);
    }

    @Override
    public List<Person> findUsers(String userName) {
        return findByCriteria(Restrictions.like("firstName", userName, MatchMode.START));
    }
}
