package com.op.cookcloud.helper;

import com.mongodb.*;
import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.Product;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 07.11.13
 * Time: 1:02
 * To change this template use File | Settings | File Templates.
 */
public class MongoDBHelper {

    public void saveProduct(Product product){
        DB db = getDB();
        DBCollection mgProduct = db.getCollection("product");

        BasicDBObject document = new BasicDBObject();
        document.put("name", product.getName());
        document.put("barcode", product.getBarcode());
        document.put("description", product.getDescription());

        mgProduct.insert(document);

    }

    public Product getProduct(String code){
        DB db = getDB();
        DBCollection mgProduct = db.getCollection("product");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("barcode", code);
        DBCursor cursor = mgProduct.find(searchQuery);
        if (!cursor.hasNext()) return null;
        DBObject dbObject = cursor.next();
        Product product = new Product();
        product.setName((String)dbObject.get("name"));
        product.setBarcode(code);
        return product;
    }

    private DB getDB() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return mongo.getDB(AppConstants.DBNAME);
    }


    public int count(Product product) {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection("product");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("barcode", product.getBarcode());
        DBCursor cursor = mgProduct.find(searchQuery);
        return cursor.count();  //To change body of created methods use File | Settings | File Templates.
    }

    public void delProduct(Product product) {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection("product");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("barcode", product.getBarcode());
        mgProduct.findAndRemove(searchQuery);
    }
}
