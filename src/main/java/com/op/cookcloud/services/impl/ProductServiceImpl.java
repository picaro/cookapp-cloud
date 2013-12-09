package com.op.cookcloud.services.impl;

import com.op.cookcloud.dao.impl.ProductDao;
import com.op.cookcloud.model.base.Product;
import com.op.cookcloud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 08.12.13
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;


    @Override
    public void saveOrUpdate(Product product) {
        if (product != null) {
            productDao.saveOrUpdate(product);
        }
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(String.valueOf(id));
    }

    @Override
    public void delete(Product product) {
       productDao.delete(product);

    }
}
