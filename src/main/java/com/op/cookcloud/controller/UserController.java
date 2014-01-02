package com.op.cookcloud.controller;

import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.form.PersonGender;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String getCreateUsePage(@ModelAttribute Person person, Model model) {
        person = new Person();
        List<String> gender = PersonGender.getValues();
        model.addAttribute("gender", gender);
        model.addAttribute(person);
        return "newUser";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute Person person) {
        person.setPassword(DigestUtils.md5Hex(person.getPassword()));
        this.personDao.saveOrUpdate(person);
        return "redirect:/login";
    }
}
