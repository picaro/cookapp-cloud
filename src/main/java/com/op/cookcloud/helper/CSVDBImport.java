package com.op.cookcloud.helper;

import com.op.cookcloud.model.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 09.11.13
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class CSVDBImport {


    public static void main(String[] arg) throws IOException {
        MongoDBHelper mongoDBHelper = new MongoDBHelper();

        String csvFile = "d:\\Projects\\Github\\cookapp-cloud\\db\\uapricesdb_utf.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                try {
                    System.out.print(".");
                    //System.out.println("Country [code= " + country[0]
                    //        + " , name=" + country[2] + "]");

                    Product product = new Product();
                    product.setName(country[0].trim());
                    product.setDescription(country[0].trim());
                    product.setLang("UA");
                    String ean = country[1].trim();
                    if (ean != null && ean.length() > 0) {
                        product.setEan(ean);
                        //if (mongoDBHelper.getProductByEAN(ean, "UA") == null) {
                            mongoDBHelper.saveProduct(product);
                        //}
                    }
                } catch (Exception ex) {

                }

                //"Нектар ""Соковита"" білий виноград-яблуко 1л";4820016252028;Б;03.Соки;ТМ Соковита
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }
}
