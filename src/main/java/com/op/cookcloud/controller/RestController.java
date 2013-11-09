package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.helper.EANdirectoryRuHelper;
import com.op.cookcloud.helper.MongoDBHelper;
import com.op.cookcloud.helper.UPCDatabaseHelper;
import com.op.cookcloud.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path("/barcode")
public class RestController {

    private static final Logger LOG = Logger.getLogger(RestController.class);

    @Autowired
    private org.codehaus.jackson.map.ObjectMapper mapper;

    @Autowired
    private MongoDBHelper mongoDBHelper;

    @Autowired
    private UPCDatabaseHelper upcDatabaseHelper;

    @Autowired
    private EANdirectoryRuHelper eaNdirectoryRuHelper;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{code}")
    public Response getProductByCode(@PathParam("code") String code) {
        LOG.info("getProductByCode:" + code);

        // String result = "" + code;//transactionBo.save();
        Product product = null;

        //1 check in our database
        Product mProduct = mongoDBHelper.getProductByEAN(code,AppConstants.EN);
        if (mProduct != null) return Response.status(200).entity(mProduct).build();

        //3 if no - get from UPC
        if (product == null) product = upcDatabaseHelper.lookUpProduct(code);//ean
        if (product == null) product = eaNdirectoryRuHelper.lookUpProduct(code);
        //mongoDBHelper.saveProduct(product,"");


        LOG.info("product:" + product.getName());
        Response.
        return Response.status(200).entity(product).build();

    }

    @POST
    @Path("{code}")
    public void addComment(@PathParam("code") String code, String comment) {
        LOG.info("addComment:" + code);
    }

//
//    @POST
//    @Path("{code}")
//    public void addPrice(@PathParam("code") String code) {
//
//    }

}