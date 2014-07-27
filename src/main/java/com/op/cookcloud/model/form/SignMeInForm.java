package com.op.cookcloud.model.form;

import lombok.Data;

import java.io.Serializable;

/**
 * User: ryan
 * Date: 2/7/13
 */
@Data
public class SignMeInForm implements Serializable{

    private String email;

    private String password;

    private boolean active = false;

    private boolean loggedIn;
}
