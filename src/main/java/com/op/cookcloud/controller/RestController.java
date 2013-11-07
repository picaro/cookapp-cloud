package com.op.cookcloud.controller;

import com.op.cookcloud.helper.UPCDatabaseHelper;
import com.op.cookcloud.model.Product;
import com.sun.jersey.spi.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

//import com.mkyong.transaction.TransactionBo;

@Component
@Path("/barcode")
public class RestController {

    private static final Logger LOG = Logger.getLogger(RestController.class);

    @Autowired
    private org.codehaus.jackson.map.ObjectMapper mapper;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{code}")
    public Response getProductByCode(@PathParam("code") String code) {
        LOG.info("getProductByCode:" + code);

        // String result = "" + code;//transactionBo.save();
        Product product = new Product();

        Map result = UPCDatabaseHelper.searchUPCdatabase("upc", code);

        System.out.println(result);
        if (result != null && !result.get("status").equals("fail")) {
            //String resultSize = result.get("size").toString();
            String resultDesc = (String) result.get("description");
            String name = "";
            if (resultDesc != null) {
                resultDesc.substring(0, resultDesc.indexOf(' '));
                if (name.length() <= 4) name = resultDesc.substring(0, 8);
                product.setName(name);
                product.setDescription(resultDesc);
            }
            product.setCookSeconds(60);
        }


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