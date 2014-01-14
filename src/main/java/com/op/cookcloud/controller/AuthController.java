package com.op.cookcloud.controller;

//import static com.op.cookcloud.AppConstants.*;
//
//import org.json.JSONException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import java.util.HashMap;
//import java.util.Map;

import static com.op.cookcloud.AppConstants.*;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.*;

import com.op.cookcloud.model.base.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static org.springframework.security.core.context.SecurityContextHolder.*;

/**
 * @author raspad
 * @version 1.0 04.01.2013
 */
@Controller
@Path("/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class);

    @Context
    private HttpServletRequest request;

    @Autowired
    private AuthenticationManager cookCloudAuthenticationManager;

//    @Context private HttpServletRequest request;
//
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

    @POST
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Consumes({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Path("/login")
//    public Authentication loginUser(@FormParam("login") String login,
//                                    @FormParam("password") String password) {
    public Authentication loginUser(@ModelAttribute Person person) {

//        logger.debug("Try to login user:" + login);
        logger.debug("Try to login user:" + person.getEmail());

        // TODO add try/catch in case bad credentials

//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword());
        Authentication authentication = cookCloudAuthenticationManager.authenticate(token);
        getContext().setAuthentication(authentication);

        HttpSession session = request.getSession();
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, getContext());
        return authentication;
    }

    @POST
    @Path("/logout")
    public void removeUserAuthentication() {

        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(SPRING_SECURITY_CONTEXT_KEY);
        securityContext.setAuthentication(null);

        // TODO send some response
    }
}