package com.op.cookcloud.security;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j
public class AuthenticationHelper {


    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication loginUser(String phoneNumber, String password, HttpServletRequest request) {
        log.trace("loginUser:" + phoneNumber);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(phoneNumber, password);
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        log.trace("loginUser end:" + phoneNumber);
        return authentication;
    }

    public void removeUserAuthentication(HttpServletRequest request) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        securityContext.setAuthentication(null);
    }

}
