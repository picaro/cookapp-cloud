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

    public void save(ShopList pojo) {
        saveOrUpdate(pojo);
    }

    public List<ShopList> findByNote(String note) {
        return findByCriteria(Restrictions.like("note", note, MatchMode.EXACT));
    }

    public List<ShopList> findByPerson(String email) {
        return getCurrentSession().createQuery("FROM ShopList s where s.person.email = '" + email + "'").list();
        //return this. findByCriteria(Restrictions.like("userid", email, MatchMode.EXACT));
    }
}
