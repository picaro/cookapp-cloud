package com.op.cookcloud.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.*;
import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.Comment;
import com.op.cookcloud.model.Product;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 07.11.13
 * Time: 1:02
 * To change this template use File | Settings | File Templates.
 */
@Service
@Log4j
public class MongoDBHelper {

    public static final String PRODUCT_TABLE = "product";
    private Gson gson = new Gson();

    private static DB db;

    public void saveProduct(Product product) {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection(PRODUCT_TABLE);
        BasicDBObject prodDoc = getBasicDBObject(product);


        mgProduct.insert(prodDoc);
    }

    private BasicDBObject getBasicDBObject(Product product) {
        BasicDBObject prodDoc = new BasicDBObject();
        prodDoc.put(AppConstants.NAME, product.getName());
        prodDoc.put(AppConstants.EAN, product.getEan());
        prodDoc.put(AppConstants.DESCRIPTION, product.getDescription());
        prodDoc.put(AppConstants.ADDDATE, product.getAddDate());
        prodDoc.put(AppConstants.COUNTRY, product.getCountry());
        prodDoc.put(AppConstants.UPC, product.getUpc());
        prodDoc.put(AppConstants.COMMENTS, gson.toJson(product.getCommentList()));
        return prodDoc;
    }

    public Product getProductByEAN(String code, String lang) {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection(PRODUCT_TABLE);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(AppConstants.EAN, code);
        DBCursor cursor = mgProduct.find(searchQuery);
        if (!cursor.hasNext()) return null;

        DBObject dbObject = cursor.next();
        Product product = new Product();
        product.setName((String) dbObject.get(AppConstants.NAME));
        product.setLang((String) dbObject.get("lang"));
        product.setEan(code);
        product.setUpc((String) dbObject.get(AppConstants.UPC));

        Type listType = new TypeToken<ArrayList<Comment>>() {
        }.getType();
        product.setCommentList(
                (List) gson.fromJson((String) dbObject.get(AppConstants.COMMENTS), listType)
        );
        cursor.close();
        return product;
    }

    private DB getDB() {
        if (db == null) {
            MongoClient mongo = null;
            try {
                mongo = new MongoClient(AppConstants.MONGO_URL,41248);


            } catch (UnknownHostException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            db = mongo.getDB("mongofood");
            log.debug("db:" + db + " pass:" + AppConstants.MONGO_PASSWORD);
            boolean auth = db.authenticate(AppConstants.MONGO_LOGIN, AppConstants.MONGO_PASSWORD.toCharArray());
        }
        return db;
    }


    public int count(Product product) {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection(PRODUCT_TABLE);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(AppConstants.EAN, product.getEan());
        DBCursor cursor = mgProduct.find(searchQuery);
        int count = cursor.count();
        cursor.close();
        return count;  //To change body of created methods use File | Settings | File Templates.
    }

    public void delProduct(Product product) {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection(PRODUCT_TABLE);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(AppConstants.EAN, product.getEan());
        mgProduct.findAndRemove(searchQuery);
    }

    public void addCommentEAN(String code, Comment comment, String lang) {
        DB db = getDB();
        Product product = getProductByEAN(code, lang);
        product.getCommentList().add(comment);
        //updateProduct(product);
        BasicDBObject change = new BasicDBObject();
        change.append(AppConstants.COMMENTS, gson.toJson(product.getCommentList()));
        BasicDBObject bproduct = getBasicDBObject(product);
        DBCollection mgProduct = db.getCollection(PRODUCT_TABLE);
        mgProduct.update(change, bproduct);
    }

    public void addCommentByEAN(String ean, Comment comment) {
        Product product = getProductByEAN(ean, AppConstants.EN);
        delProduct(product);
        product.getCommentList().add(comment);
        saveProduct(product);
    }

    public void updateProduct(Product cProduct) {
        delProduct(cProduct);
        saveProduct(cProduct);
    }

    public Boolean isUADBSaved() {
        DB db = getDB();
        DBCollection settings = db.getCollection("settings");
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = settings.find(searchQuery);
        log.info("isUADBSaved");
        //    if (cursor != null)
        if (cursor.size() == 0) {
            return false;
        } else {
            Object retObj = cursor.next().get("isuadbsaved");
            if (retObj instanceof Boolean) {
                return (Boolean) retObj;
            } else return false;
        }
    }

    public void setUADBSaved() {
        DB db = getDB();
        DBCollection mgProduct = db.getCollection("settings");
        BasicDBObject prodDoc = new BasicDBObject();
        prodDoc.put("isuadbsaved", true);
        mgProduct.insert(prodDoc);
    }
}
