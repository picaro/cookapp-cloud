package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Product;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao extends AbstractDaoImpl<Product, String> {

    protected ProductDao() {
        super(Product.class);
    }

    public void saveUser(Product user) {
        saveOrUpdate(user);
    }

    public List<Product> findUsers(String userName) {
        return findByCriteria(Restrictions.like("firstName", userName, MatchMode.START));
    }
}
