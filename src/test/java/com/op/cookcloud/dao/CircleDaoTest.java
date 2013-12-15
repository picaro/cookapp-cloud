package com.op.cookcloud.dao;

import com.op.cookcloud.model.base.Circle;
import org.junit.Test;

import static com.mongodb.util.MyAsserts.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.12.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */

public class CircleDaoTest extends BaseDaoTest {

    @Test
    public void testRead() {
        Circle circle = createCircle();
        Circle testCircle = circleDao.findById(1);
        assertNotNull(testCircle);
        assertEquals(1, testCircle.getId());
        assertEquals("семья", testCircle.getName());
        assertEquals("любим готовить", testCircle.getNote());
        circleDao.delete(circle);
    }

    @Test
    public void testUpdate() {
        Circle circle = createCircle();
        circle.setName("тестовая семья");
        circleDao.saveOrUpdate(circle);
        Circle testCircle = circleDao.findById(1);
        assertNotNull(testCircle);
        assertEquals("тестовая семья", testCircle.getName());
        circleDao.delete(circle);

    }

    @Test
    public void testCreateDelete() throws Exception {
        Circle userSettings = new Circle();
        circleDao.save(userSettings);
        assertTrue(circleDao.findAll().size() == 1);
        Circle settingsNew = circleDao.findById(userSettings.getId());
        assertNotNull(settingsNew);
        circleDao.delete(settingsNew);
        assertTrue(circleDao.findAll().size() == 0);
    }

}
