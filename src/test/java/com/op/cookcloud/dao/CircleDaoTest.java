package com.op.cookcloud.dao;

import com.op.cookcloud.dao.impl.CircleDao;
import com.op.cookcloud.dao.impl.UserSettingsDao;
import com.op.cookcloud.model.base.Circle;
import com.op.cookcloud.model.base.UserSettings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.12.13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */

public class CircleDaoTest extends BaseDaoTest{

    @Autowired
    protected CircleDao circleDao;

    @Test
    public void testCreateDelete() throws Exception {
        Circle userSettings = new Circle();
        //userSettings. setPerson(createPerson());
        circleDao.save(userSettings);

        assertTrue(circleDao.findAll().size() == 1);
        Circle settingsNew = circleDao.findById(userSettings.getId());
        circleDao.delete(settingsNew);
        assertTrue(circleDao.findAll().size() == 0);
    }

}
