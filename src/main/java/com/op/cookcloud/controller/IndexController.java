package com.op.cookcloud.controller;

import com.op.cookcloud.model.form.SignInForm;
import com.op.cookcloud.model.form.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * User: ryan
 * Date: 2/7/13
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index", "signupForm", new SignupForm());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "index";
        }
        return "show";
    }

    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signIn(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signIn2(@ModelAttribute("signinform") SignInForm person, Model model){
        return "login";
    }
}


