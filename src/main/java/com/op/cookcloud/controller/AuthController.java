package com.op.cookcloud.controller;

import static com.op.cookcloud.AppConstants.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author raspad
 * @version 1.0 04.01.2013
 */
@Controller
@Path("/auth")
//@RequestMapping("/auth")
public class AuthController {

    @Context private HttpServletRequest request;

    @Context private HttpServletResponse response;

    @POST
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Path("/login")
    public @ResponseBody JSONObject login(final @QueryParam(value="auth") String auth)
            throws JSONException, IOException {

        JSONObject result = new JSONObject();
        if ("SUCCESS".equalsIgnoreCase(auth)) {
            String sessionId = request.getSession().getId();
            result.put("SESSION_ID", sessionId);
            result.put("MESSAGE", "Successfully logged in");
        } else if ("FAILURE".equalsIgnoreCase(auth)) {
            result.put("ERROR_CODE", HttpServletResponse.SC_UNAUTHORIZED);
            result.put("MESSAGE", "AUTHENTICATION_FAILURE");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong url");
        }

        return result;
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON + CHARSET_UTF_8})
    @Path("/logout")
    public @ResponseBody JSONObject logout() throws JSONException {
        JSONObject result = new JSONObject();
        result.put("MESSAGE", "Successfully logged out");
        return result;
    }
}