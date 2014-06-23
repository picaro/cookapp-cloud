package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Person;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao extends AbstractDaoImpl<Person, Integer> {

    private static final Logger LOG = Logger.getLogger(PersonDao.class);

    protected PersonDao() {
        super(Person.class);
    }

    public void saveUser(Person user) {
        saveOrUpdate(user);
    }

    public Person findUserByMail(String email) {
        return findByCriteria(Restrictions.like("email", email, MatchMode.EXACT)).get(0);
    }
}
