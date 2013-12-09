package com.op.cookcloud.controller.crud;

import com.op.cookcloud.model.base.Product;
import com.op.cookcloud.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Service("productService")
@Transactional(readOnly = true)
@Path("/product")
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response read(@PathVariable Long id) {
        Product product = productService.findById(id);
        LOG.info("Find product " + product + "by id" + id);
        return Response.status(200).entity(product).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable Long id, @RequestBody Product product) {
        LOG.info("Updating product " + product);
        productService.saveOrUpdate(product);
        LOG.info("Product update successfully " + product);

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public void create(@RequestBody Product product) {
        LOG.info("Creating product " + product);
        productService.saveOrUpdate(product);
        LOG.info("Product crated  successfully " + product);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        Product product = productService.findById(id);
        LOG.info("Deleting product " + product);
        productService.delete(product);
        LOG.info("Product deleted successfully " + product);
    }


}