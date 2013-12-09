package com.op.cookcloud.services;


import com.op.cookcloud.model.base.Product;

public interface ProductService {

    public void saveOrUpdate(Product product);

    public Product findById(Long id);

    public  void delete(Product product);

}
