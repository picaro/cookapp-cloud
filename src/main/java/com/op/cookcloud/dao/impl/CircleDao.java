package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Circle;
import org.springframework.stereotype.Repository;

@Repository
public class CircleDao extends AbstractDaoImpl<Circle, Integer> {

    protected CircleDao() {
        super(Circle.class);
    }

    public void save(Circle circle) {
        saveOrUpdate(circle);
    }
}
