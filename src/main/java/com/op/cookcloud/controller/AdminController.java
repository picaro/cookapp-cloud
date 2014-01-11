package com.op.cookcloud.controller;

import com.op.cookcloud.dao.impl.*;
import com.op.cookcloud.model.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 23.12.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AdminController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ShopListDao shopListDao;

    @Autowired
    private CircleDao circleDao;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/admin/persons", method = RequestMethod.GET)
    public String adminUserList(Model model) {
        List<Person> persons = personDao.findAll();
        model.addAttribute("persons", persons);
        return "persons";
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String adminProductList(Model model) {
        List<Product> productList = productDao.findAll();
        model.addAttribute("productList", productList);
        return "products";
    }

    @RequestMapping(value = "/admin/shops", method = RequestMethod.GET)
    public String adminShopList(Model model) {
        List<ShopList> shopList = shopListDao.findAll();
        model.addAttribute("shopList", shopList);
        return "shops";
    }

    @RequestMapping(value = "/admin/shop", method = RequestMethod.GET)
    public String adminShop (Model model) {
        List<Shop> shopList = shopDao.findAll();
        model.addAttribute("shopList", shopList);
        return "shop";
    }

    @RequestMapping(value = "/admin/circles", method = RequestMethod.GET)
    public String adminCircle (Model model) {
        List<Circle> circleList = circleDao.findAll();
        model.addAttribute("circleList", circleList);
        return "circles";
    }

}