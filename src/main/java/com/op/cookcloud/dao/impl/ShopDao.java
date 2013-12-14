package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Shop;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDao extends AbstractDaoImpl<Shop, Integer> {

    protected ShopDao() {
        super(Shop.class);
    }

    public void save(Shop shop) {
        saveOrUpdate(shop);
    }


}
