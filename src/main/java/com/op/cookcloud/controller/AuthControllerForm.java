package com.op.cookcloud.controller;

import com.op.cookcloud.model.form.SignMeInForm;
import com.op.cookcloud.security.AuthenticationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @ModelAttribute("signInForm")
    public SignMeInForm createAttrSignInForm() {
        return new SignMeInForm();
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signInForm(@Valid SignMeInForm signInForm ,
                             BindingResult result, Model model,
                             HttpServletRequest request,
                             HttpServletResponse response){
        Authentication authentication =
                authenticationHelper.loginUser(signInForm.getEmail(), signInForm.getPassword(),  request);
        //log.info("authentication:" + authentication.isAuthenticated());
        if (authentication == null || !(authentication.getPrincipal() instanceof User)){
            model.addAttribute("error", "User or login incorrect!");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //modelView.addObject("errorMessage", errorMessage);

            return "login";
        }
        signInForm.setLoggedIn(authentication.isAuthenticated());
        return "redirect:/";
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