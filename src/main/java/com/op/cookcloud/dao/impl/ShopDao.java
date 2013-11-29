package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Shop;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDao extends AbstractDaoImpl<Shop, String> {

    protected ShopDao() {
        super(Shop.class);
    }

    public void saveUser(Shop user) {
        saveOrUpdate(user);
    }

    public List<Shop> findUsers(String userName) {
        return findByCriteria(Restrictions.like("firstName", userName, MatchMode.START));
    }
}
