package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ProductDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.base.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Service("productService")
@Transactional
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class);
    @Autowired
    private ProductDao productDao;


    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @RequestMapping(value = "/allProducts")
    public ModelAndView readAll() {
        List<Product> productList = productDao.findAll();
        return new ModelAndView("products" , "productList" ,productList);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Product read(@PathParam("id") Integer id) {
        Product product = productDao.findById(id);
        LOG.debug("Find product " + product + "by id" + id);
        return product;
    }

    @PUT
    public void update(@ModelAttribute Product product) {
        productDao.saveOrUpdate(product);
        LOG.debug("Product update: " + product);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Product product) {
        productDao.saveOrUpdate(product);
        LOG.debug("Product crated: " + product);

    }

    @DELETE
    @Path("{id}")
    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam(value = "id", required = true) Integer id) {
        Product product = productDao.findById(id);
        LOG.info("Deleting product " + product);
        productDao.delete(product);
        LOG.debug("Product deleted: " + product);
        return new ModelAndView("admin");
    }


}