package com.op.cookcloud.controller;

import com.op.cookcloud.dao.impl.PersonDao;
import com.op.cookcloud.model.base.Person;
import com.op.cookcloud.model.form.Gender;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String getCreateUsePage(@ModelAttribute Person person, Model model) {
        person = new Person();
        List<String> gender = Gender.getValues();
        model.addAttribute("gender", gender);
        model.addAttribute(person);
        return "userRegistration";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute Person person, BindingResult bindingResult, Model model) {
        model.addAttribute("error", bindingResult);
        if(bindingResult.hasErrors()){
            return  "userRegistration";
        }
        person.setPassword(DigestUtils.md5Hex(person.getPassword()));
        this.personDao.saveOrUpdate(person);
        return "redirect:/login";
    }
}
