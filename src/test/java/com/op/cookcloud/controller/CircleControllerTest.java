package com.op.cookcloud.controller;

import com.op.cookcloud.dao.BaseDaoTest;
import com.op.cookcloud.model.base.Circle;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: picaro
 * Date: 15.12.13
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
@Log4j
public class CircleControllerTest extends BaseDaoTest{

    @Autowired
    private CircleController circleController;

    @Test
    public void getCirclesTest(){
        Circle circle = new Circle();
        circle.setName("aaa");
        circleController.create(circle);

        List<Circle> circleL = circleDao.findAll();
        assertEquals(circleL.size(),1);

        Circle circle1 = circleController.read(circleL.get(0).getId());
        assertEquals(circle1.getName(), "aaa");
        log.debug(circle1.getId());
    }

    @Test
    public void deleteCirclesTest(){
        Circle circle = new Circle();
        circle.setName("aaa");
        circleController.create(circle);

        List<Circle> circleL = circleDao.findAll();
        assertEquals(circleL.size(),1);

        circleController.delete(circleL.get(0).getId());

        assertEquals(circleDao.findAll().size(), 0);
    }

    @Test
    public void updateCirclesTest(){
        Circle circle = new Circle();
        circle.setName("aaa");
        circleController.create(circle);

        List<Circle> circleL = circleDao.findAll();
        assertEquals(circleL.size(),1);

        Circle circle1 = circleL.get(0);
        circle1.setName("new note");
        circleController.update(circle1);


        circleL = circleDao.findAll();
        assertEquals(circleL.get(0).getName(),"new note");
    }




}
