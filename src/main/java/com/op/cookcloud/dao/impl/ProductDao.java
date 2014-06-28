package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Product;
import com.op.cookcloud.model.base.ShopList;
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

    public List<Product> findByShopList(ShopList shopList) {
        return findByCriteria(Restrictions.eq("shoplist", shopList));
    }

}
