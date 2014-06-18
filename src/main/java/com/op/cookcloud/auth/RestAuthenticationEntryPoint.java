package com.op.cookcloud.auth;

import com.op.cookcloud.AppConstants;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raspad
 * @version 1.0 02.01.2013
 */
@Log4j
@Component(value="restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Method rejects every unauthenticated request and send error code 401.
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        log.info("restAuthEntry");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, AppConstants.UNAUTHORIZED);
    }
}
