package com.op.cookcloud.dao;

import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.dao.impl.UserSettingsDao;
import com.op.cookcloud.model.base.Shop;
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

public class UserSettingsDaoTest extends BaseDaoTest{

    @Autowired
    protected UserSettingsDao userSettingsDao;

    @Test
    public void testCreateDelete() throws Exception {
        UserSettings userSettings = new UserSettings();
        userSettings.setPerson(createPerson());
        userSettingsDao.save(userSettings);

        assertTrue(userSettingsDao.findAll().size() == 1);
        UserSettings settingsNew = userSettingsDao.findById(userSettings.getId());
        userSettingsDao.delete(settingsNew);
        assertTrue(userSettingsDao.findAll().size() == 0);
    }

}
