package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Product;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao extends AbstractDaoImpl<Product, Integer> {

    protected ProductDao() {
        super(Product.class);
    }

    public void save(Product product) {
        saveOrUpdate(product);
    }

    public List<Product> findUsers(String userName) {
        return findByCriteria(Restrictions.like("firstName", userName, MatchMode.START));
    }
}
