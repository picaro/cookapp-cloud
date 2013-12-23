package com.op.cookcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 23.12.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage() {
        return "admin";
    }



}