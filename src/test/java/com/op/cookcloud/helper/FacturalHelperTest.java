package com.op.cookcloud.helper;

import com.op.cookcloud.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 07.11.13
 * Time: 1:42
 * To change this template use File | Settings | File Templates.
 */
public class FacturalHelperTest {

    @Test
    public void getProduct(){
        //EANdirectoryRuHelper ruHelper = new EANdirectoryRuHelper();
        FacturalHelper facturalHelper = new FacturalHelper();
        System.out.println("aaa");
        Product product = facturalHelper.lookUpProduct("0073010013469");
        assertEquals("Tampons With Plastic Applicators Unscented Duo Pack Regular Super",product.getName());
    }

}