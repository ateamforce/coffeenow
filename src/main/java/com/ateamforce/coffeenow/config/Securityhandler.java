/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.config;

import com.ateamforce.coffeenow.model.Administrator;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.Client;
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.service.AppUserService;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author alexa
 */
@Component
public class Securityhandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    AppUserService appUserService;

    private final static Logger LOGGER = Logger.getLogger(Securityhandler.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Collection<GrantedAuthority> authorities = authUser.getAuthorities();

        String targetUrl = "";

        for (GrantedAuthority authority : authorities) {

            if (authority.getAuthority().equals("admin")) {

                targetUrl = "/administrator/dashboard";
            } else if (authority.getAuthority().equals("store")) {

                targetUrl = "/store/dashboard";
            } else if (authority.getAuthority().equals("client")) {

                targetUrl = "/client/dashboard";
            }

        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();
        }
        clearAuthenticationAttributes(request);

        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    @Override
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
