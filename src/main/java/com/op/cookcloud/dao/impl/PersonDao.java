package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        log.debug("findUserByMail find user : {}" , email);
        List<Person> users = findByCriteria(Restrictions.like("email", email, MatchMode.EXACT));
        if (users.size() == 0)
        {
            log.info("user not found: {}" , email);
            throw new UsernameNotFoundException("No user with this name");
        }
        log.info("users found : {}", users.size());
        return users.get(0);
    }

    public boolean doesUserExistWithEmail(String email) {
        try {
            findUserByMail(email);
        } catch (UsernameNotFoundException e){
            return false;
        }
        return true;
    }
}
