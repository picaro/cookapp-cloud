package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class PersonDao extends AbstractDaoImpl<Person, Integer> {

    protected PersonDao() {
        super(Person.class);
    }

    public void saveUser(Person user) {
        saveOrUpdate(user);
    }

    public Person findUserByMail(String email) {
        log.info("findUserByMail" , "find user : " + email);
        return findByCriteria(Restrictions.like("email", email, MatchMode.EXACT)).get(0);
    }

}
