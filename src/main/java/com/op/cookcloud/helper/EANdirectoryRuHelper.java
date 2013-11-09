package com.op.cookcloud.helper;

import com.op.cookcloud.model.Product;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 09.11.13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EANdirectoryRuHelper implements BarcodeSearcher {

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
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://eandirectory.ru/barcode/" + code);
        HttpResponse response = null;
        Product product = new Product();
        try {
            response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            StringBuilder textView = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                textView.append(line);
            }

            String page = textView.toString();
            if(page.indexOf("Штрих-код EAN-13") <=0) return null;
            page = page.substring(page.indexOf("Штрих-код EAN-13"));
            page = page.substring(0, page.indexOf("</tbody>"));
            while(page.indexOf("<tr>") > 0){
                String type = page.substring(page.indexOf("<td>")+4,  page.indexOf("</td>"));
                page = page.substring(page.indexOf("</td>")+4);
                String value = page.substring(page.indexOf("<td>")+4,  page.indexOf("</td>"));
                page = page.substring(page.indexOf("</tr>"));
                if (type.equals("Описание (ориг.)")) product.setDescription(value);
                if (type.equals("Страна регистрации")) product.setCountry(value);
                //Торговая марка
                //  Категория
                //Страна производства
                //Модель/Артикул
            }

            product.setEan(code);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
