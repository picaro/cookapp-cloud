package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ProductDao;
import com.op.cookcloud.model.base.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Service("productService")
@Transactional(readOnly = true)
@Path("/product")
public class ProductController{

    private static final Logger LOG = Logger.getLogger(ProductController.class);
    @Autowired
    private ProductDao productDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    @Path("{id}")
    public Product read(@PathParam("id") int id) {
        Product product = productDao.findById(id);
        LOG.info("Find product " + product + "by id" + id);
        return product;
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") int id) {
        LOG.info("Updating product ");
        Product product = productDao.findById(id);
        productDao.saveOrUpdate(product);
        LOG.info("Product update successfully " + product);

    }

    @POST
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public void create(@ModelAttribute Product product) {
        LOG.info("Creating product " + product);
        productDao.saveOrUpdate(product);
        LOG.info("Product crated  successfully " + product);

    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        Product product = productDao.findById(id);
        LOG.info("Deleting product " + product);
        productDao.delete(product);
        LOG.info("Product deleted successfully " + product);
    }


}