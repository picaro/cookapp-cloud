package com.op.cookcloud.helper;

import com.op.cookcloud.model.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 07.11.13
 * Time: 1:42
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
public class EANDirectoryHelperTest {

    @Autowired
    private EANdirectoryRuHelper ruHelper;


    @Before
    public void setUp() {
    }

    @Test
    @Ignore
    public void getProduct() {
        System.out.println("getProduct");
        Product product = ruHelper.lookUpProduct("5410306866760");
        assertNotNull(product);
        assertEquals("ACTIFF Javel Ср-во д/кухни спрей 500мл", product.getDescription());
    }

}