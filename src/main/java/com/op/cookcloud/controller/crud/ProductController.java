package com.op.cookcloud.controller.crud;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ProductDao;
import com.op.cookcloud.model.base.Circle;
import com.op.cookcloud.model.base.Person;
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
public class ProductController implements CRUDController<Product>{

    private static final Logger LOG = Logger.getLogger(ProductController.class);
    @Autowired
    private ProductDao productDao;

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
    public void delete(@PathParam("id") Integer id) {
        Product product = productDao.findById(id);
        LOG.info("Deleting product " + product);
        productDao.delete(product);
        LOG.debug("Product deleted: " + product);
    }


}