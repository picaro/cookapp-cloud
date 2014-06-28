package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Role;
import com.op.cookcloud.model.base.ShopList;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class RoleDao extends AbstractDaoImpl<Role, Integer> {

    protected RoleDao() {
        super(Role.class);
    }

    public List<Role> findByPerson(Person person) {
        return findByCriteria(Restrictions.eq("person", person));
    }

}
