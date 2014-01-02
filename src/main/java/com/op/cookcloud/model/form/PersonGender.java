package com.op.cookcloud.model.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 30.12.13
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public enum PersonGender {

    MALE("M"),
    FEMALE("F");

    private String value;

    private PersonGender(String value) {
        this.value = value;
    }

    public static List<String> getValues() {
        List<String> result = new ArrayList<String>();
        for (PersonGender value : PersonGender.values()) {
            result.add(value.value);
        }
        return result;
    }

}
