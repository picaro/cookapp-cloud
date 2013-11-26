package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.helper.*;
import com.op.cookcloud.model.Comment;
import com.op.cookcloud.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


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
    private FacturalHelper facturalHelper;

    @Autowired
    private EANdirectoryRuHelper eaNdirectoryRuHelper;

    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Path("{code}")
    public Response getProductByCode(@PathParam("code") String code) {
        LOG.info("getProductByCode:" + code);

        // String result = "" + code;//transactionBo.save();
        Product product = null;

        //1 check in our database
        Product mProduct = mongoDBHelper.getProductByEAN(code, AppConstants.EN);
        if (mProduct != null) return Response.status(200).entity(mProduct).build();

        //3 if no - get from UPC
        if (product == null) product = facturalHelper.lookUpProduct(code);//ean
        if (product == null) product = upcDatabaseHelper.lookUpProduct(code);//ean
        if (product == null) product = eaNdirectoryRuHelper.lookUpProduct(code);
        if (product != null) mongoDBHelper.saveProduct(product);


        Response response = Response.status(200).entity(product).build();
        return response;

    }

    @POST
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addComment(@PathParam("code") String code, Comment comment) {

        mongoDBHelper.addCommentByEAN(code, comment);

        LOG.info("addComment:" + comment.getComment());
    }

    @GET
    @Path("/updatedbfromsql")
    public void updateDB() {
        LOG.info("updatedbfromsql start");
        if (!mongoDBHelper.isUADBSaved()) {
            LOG.info("!saved");
            CSVDBImport csvdbImport = new CSVDBImport();
            try {
                csvdbImport.importDB();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mongoDBHelper.setUADBSaved();
            LOG.info("saved");
        }
        LOG.info("updatedbfromsql end");
    }




}