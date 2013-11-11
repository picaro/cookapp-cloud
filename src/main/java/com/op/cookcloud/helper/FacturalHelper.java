package com.op.cookcloud.helper;

import com.factual.driver.Factual;
import com.factual.driver.FieldFilter;
import com.factual.driver.Query;
import com.factual.driver.Tabular;
import com.op.cookcloud.model.Product;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 09.11.13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FacturalHelper implements BarcodeSearcher {

    @Override
    public Product lookUpProductByEAN(String code) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Product lookUpProductByUPC(String code) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Product lookUpProduct(String code) {
       Product product = new Product();

        Factual factual = new Factual("xxFiqmh1H26jMWUXmmVrexYbwIIL3RE269Dk8hUx", "mFEshzJuARYclNyoBPmeOZr9wyduWGq9LTm5aQpT");
        Query query = new Query();
        query.add(new FieldFilter("$bw", "ean13", code));//
       // query.field("ean13").equals("0073010013469");
        Tabular tabular = factual.fetch("products-cpg-nutrition",query);

        Iterator<Map<String,Object>> iterator = tabular.getData().iterator();
        if (iterator.hasNext()) {
            Map<String,Object> resp = iterator.next();
            product.setDescription((String)resp.get(""));
            product.setName((String)resp.get("product_name"));
            product.setEan(code);
            product.setCountry((String)resp.get(""));
        } else {
            return null;
        }

        return product;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
