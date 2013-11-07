package com.op.cookcloud;

import com.mongodb.*;
import com.op.cookcloud.config.SpringMongoConfig;
import com.op.cookcloud.model.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.net.UnknownHostException;

public class Maint{

    public static void main(String [] arg){
        try {
            MongoClient mongo = new MongoClient( "localhost" , 27017 );
            DB db = mongo.getDB(AppConstants.DBNAME);

            DBCollection mgProduct = db.getCollection("product");

            BasicDBObject document = new BasicDBObject();
            document.put("name", "mkyong");
            document.put("age", 30);

            mgProduct.insert(document);

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", "mkyong");
            DBCursor cursor = mgProduct.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            //boolean auth = db.authenticate("username", "password".toCharArray());
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}