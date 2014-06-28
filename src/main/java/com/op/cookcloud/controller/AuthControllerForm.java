package com.op.cookcloud.controller;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.form.SignInForm;
import com.op.cookcloud.security.AuthenticationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signIn(){
        return "login";
    }

    @Autowired
    private AuthenticationHelper authenticationHelper;


    @ModelAttribute("signinform")
    public SignInForm getSignInForm() {
        return new SignInForm();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signIn2(SignInForm signInForm , Model model, HttpServletRequest request){
        Authentication authentication = authenticationHelper.loginUser(signInForm.getEmail(), signInForm.getPassword(),  request);
        log.info("authentication:" + authentication.isAuthenticated());
        signInForm.setLoggedIn(authentication.isAuthenticated());
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request) {
        log.info("authentication logout");
        authenticationHelper.removeUserAuthentication(request);
        return "redirect:/";
    }

}