package com.op.cookcloud.dao.impl;

import com.op.cookcloud.model.base.UserSettings;
import org.springframework.stereotype.Repository;

@Repository
public class UserSettingsDao extends AbstractDaoImpl<UserSettings, Integer> {

    protected UserSettingsDao() {
        super(UserSettings.class);
    }

    public void save(UserSettings userSettings) {
        saveOrUpdate(userSettings);
    }

}
