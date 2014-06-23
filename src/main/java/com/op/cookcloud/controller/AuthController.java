package com.op.cookcloud.controller;

import static com.op.cookcloud.AppConstants.*;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.dao.impl.ShopListDao;
import com.op.cookcloud.model.base.ShopList;
import com.op.cookcloud.model.form.SignInForm;
import com.op.cookcloud.security.AuthenticationHelper;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author raspad
 * @version 1.0 04.01.2013
 */
@Service("loginService")
@Path("/auth")
@Transactional
@Log4j
public class AuthController {

    @Autowired
    private AuthenticationHelper authenticationHelper;

//    @GET
//    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
//    @Path("/success")
//    public Map<String, Object> loginSuccess() throws JSONException {
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        String sessionId = request.getSession().getId();
//        result.put(SESSION_ID, sessionId);
//        result.put(MESSAGE, "Successfully logged in");
//        return result;
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
//    @Path("/failure")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Map<String, Object> loginFailure() throws JSONException {
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(ERROR_CODE, HttpServletResponse.SC_UNAUTHORIZED);
//        result.put(MESSAGE, "Authentication failure");
//        return result;
//    }
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
//    @Path("/logout")
//    public Map<String, Object> logout() throws JSONException {
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(MESSAGE, "Successfully logged out");
//        return result;
//    }

    @Autowired
    private ShopListDao shopListDao;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public ModelAndView loginUser(@RequestBody SignInForm signInForm) {
//        ModelAndView modelView = new ModelAndView();
//        Authentication authentication = authenticationHelper.loginUser(signInForm.getEmail(), signInForm.getPassword(), request);
//        log.info("authentication:" + authentication.isAuthenticated());
//        //modelView.addObject("user", signInForm);
//        modelView.getModel().put("user", signInForm);
//        modelView.setView(new RedirectView(""));
//        //modelView.setc
//        //return "aaa";
        List<ShopList> shopList = shopListDao.findAll();
        return new ModelAndView("shops", "shopList", shopList);
    }

}