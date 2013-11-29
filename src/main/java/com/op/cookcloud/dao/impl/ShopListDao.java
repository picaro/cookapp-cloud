package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.ShopList;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "shopListDao")
public class ShopListDao extends AbstractDaoImpl<ShopList, Integer> {

    protected ShopListDao() {
        super(ShopList.class);
    }

    public void saveUser(ShopList user) {
        saveOrUpdate(user);
    }

    public List<ShopList> findUsers(String userName) {
        return findByCriteria(Restrictions.like("firstName", userName, MatchMode.START));
    }
}
