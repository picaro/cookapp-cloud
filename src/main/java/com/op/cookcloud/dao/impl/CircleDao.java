package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Circle;
import com.op.cookcloud.model.base.Person;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CircleDao extends AbstractDaoImpl<Circle, String> {

    protected CircleDao() {
        super(Circle.class);
    }

    public void saveUser(Circle circle) {
        saveOrUpdate(circle);
    }

    public List<Circle> findUsers(String userName) {
        return findByCriteria(Restrictions.like("firstName", userName, MatchMode.START));
    }
}
