package com.op.cookcloud.controller;

import static com.op.cookcloud.AppConstants.*;

import com.op.cookcloud.AppConstants;
import com.op.cookcloud.model.form.SignMeInForm;
import com.op.cookcloud.security.AuthenticationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raspad
 * @version 1.0 04.01.2013
 */
@Service("loginService")
@Path("/auth")
@Transactional
@Slf4j
public class AuthControllerRest {

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON + AppConstants.CHARSET_UTF_8})
    public SignMeInForm loginUser(@RequestBody SignMeInForm signInForm, @Context HttpServletRequest request) {
        Authentication authentication = authenticationHelper.loginUser(signInForm.getEmail(), signInForm.getPassword(), request);
        log.info("authentication:" + authentication.isAuthenticated());
        signInForm.setLoggedIn(authentication.isAuthenticated());
        return signInForm;
    }

    @GET
    @Path("/logout")
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    public Map<String, Object> logoutUser(@Context HttpServletRequest request) {
        log.info("authentication logout");
        authenticationHelper.removeUserAuthentication(request);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(MESSAGE, "Successfully logged out");
        return result;
    }

}