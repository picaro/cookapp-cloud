package com.op.cookcloud.helper;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.controller.RestController;
import com.op.cookcloud.model.Comment;
import com.op.cookcloud.model.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        "classpath:/applicationContext-test.xml","classpath:/hibernateContext.xml"})
public class EANDirectoryHelperTest {

    ApplicationContext ac;

    @Before
    public void setUp()
    {
    }

    EANdirectoryRuHelper ruHelper;

    @Test
    public void getProduct(){
        System.out.println("111");
        //EANdirectoryRuHelper ruHelper = new EANdirectoryRuHelper();
     //   ruHelper = (EANdirectoryRuHelper) ac.getBean("EANdirectoryRuHelper");

//        System.out.println("aaa");
//        Product product = ruHelper.lookUpProduct("5410306866760");
//        assertEquals("ACTIFF Javel Ср-во д/кухни спрей 500мл",product.getDescription());
    }

}