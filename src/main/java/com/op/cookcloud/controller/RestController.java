package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.helper.MongoDBHelper;
import com.op.cookcloud.helper.UPCDatabaseHelper;
import com.op.cookcloud.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;


@Component
@Path("/barcode")
public class RestController {

    private static final Logger LOG = Logger.getLogger(RestController.class);

    @Autowired
    private org.codehaus.jackson.map.ObjectMapper mapper;

    @Autowired
    private MongoDBHelper mongoDBHelper;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{code}")
    public Response getProductByCode(@PathParam("code") String code) {
        LOG.info("getProductByCode:" + code);

        // String result = "" + code;//transactionBo.save();
        Product product = new Product();

        //1 check in our database
        Product mProduct = mongoDBHelper.getProduct(code);
        if (mProduct != null) return Response.status(200).entity(mProduct).build();

        //3 if no - get from UPC
        Map result = UPCDatabaseHelper.searchUPCdatabase("ean", code);
        System.out.println(result);
        if (result != null && !result.get("status").equals("fail")) {
            if (((Boolean) result.get("found"))) {
                product.setDescription((String) result.get("description"));
                product.setName((String) result.get("description"));
                product.setSize((String) result.get("size"));
                product.setEan((String) result.get("ean"));
                product.setUpc((String) result.get("upc"));
                product.setCountry((String) result.get("issuerCountry"));
                product.setCountryCode((String) result.get("issuerCountryCode"));
                DateFormat dateFormat = new SimpleDateFormat(AppConstants.DATE_TIME_PATTERN);
                Calendar cal = Calendar.getInstance();
                product.setAddDate(dateFormat.format(cal.getTime()));
            }
        }
      //  mongoDBHelper.saveProduct(product);


        LOG.info("product:" + product.getName());
        return Response.status(200).entity(product).build();

    }

    @POST
    @Path("{code}")
    public void addComment(@PathParam("code") String code) {

    }

//
//    @POST
//    @Path("{code}")
//    public void addPrice(@PathParam("code") String code) {
//
//    }

}