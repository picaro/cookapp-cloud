package com.op.cookcloud.controller;

import static com.op.cookcloud.AppConstants.*;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raspad
 * @version 1.0 04.01.2013
 */
@Controller
@Path("/auth")
public class AuthController {

    @Context private HttpServletRequest request;

    @GET
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Path("/success")
    public Map<String, Object> loginSuccess() throws JSONException {

        Map<String, Object> result = new HashMap<String, Object>();
        String sessionId = request.getSession().getId();
        result.put(SESSION_ID, sessionId);
        result.put(MESSAGE, "Successfully logged in");
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Path("/failure")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> loginFailure() throws JSONException {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ERROR_CODE, HttpServletResponse.SC_UNAUTHORIZED);
        result.put(MESSAGE, "Authentication failure");
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Path("/logout")
    public Map<String, Object> logout() throws JSONException {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(MESSAGE, "Successfully logged out");
        return result;
    }
}