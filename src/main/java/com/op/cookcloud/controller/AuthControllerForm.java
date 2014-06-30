package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.form.SignInForm;
import com.op.cookcloud.security.AuthenticationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

import static com.op.cookcloud.AppConstants.CHARSET_UTF_8;
import static com.op.cookcloud.AppConstants.MESSAGE;

/**
 * @author raspad
 * @version 1.0 04.01.2013
 */
@Slf4j
@Controller
public class AuthControllerForm {

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signIn(){
        return "login";
    }

    @ModelAttribute("signinform")
    public SignInForm getSignInForm() {
        return new SignInForm();
    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public ModelAndView signInForm(@RequestParam(value = "error", required = false) String error,
                                   SignInForm signInForm , Model model, HttpServletRequest request){
        ModelAndView modelNV = new ModelAndView();

        Authentication authentication = authenticationHelper.loginUser(signInForm.getEmail(), signInForm.getPassword(),  request);
        log.info("authentication:" + authentication.isAuthenticated());
        if (!(authentication.getPrincipal() instanceof User)){
            model.addAttribute("error", "Incorrect user");
            modelNV.getModel().put("error","aaaa");
            modelNV.getModel().put("email",signInForm.getEmail());

            //modelNV.setViewName("login.jsp");
            return modelNV;
            // return "redirect:/login";
        }
        signInForm.setLoggedIn(authentication.isAuthenticated());
        modelNV.setViewName("redirect:/");
        return modelNV;//"redirect:/";
    }

    /**
     * just interface , could be different realisation
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request) {
        log.info("authentication logout");
        return "redirect:/j_spring_security_logout";
    }

}