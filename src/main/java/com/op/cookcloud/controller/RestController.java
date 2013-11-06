package com.op.cookcloud.controller;

import com.op.cookcloud.helper.UPCDatabaseHelper;
import com.op.cookcloud.model.Product;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Map;

//import com.mkyong.transaction.TransactionBo;

@Component
@Path("/barcode")
public class RestController {

    @GET
    @Path("{code}")
    public Response savePayment(@PathParam("code") String code) {

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


        return Response.status(200).entity(product).build();

    }


}