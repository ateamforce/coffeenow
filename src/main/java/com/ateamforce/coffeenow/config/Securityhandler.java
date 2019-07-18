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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.util.StringUtils;

/**
 *
 * @author alexa
 */
@Component
public class Securityhandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    AppUserService appUserService;

    protected final Log logger = LogFactory.getLog(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser currentUser = new AppUser();

        Collection<GrantedAuthority> authorities = authUser.getAuthorities();

        for (GrantedAuthority authority : authorities) {

            if (authority.getAuthority().equals("admin")) {
                currentUser = (Administrator) appUserService.getUserByEmail(authUser.getUsername());
            } else if (authority.getAuthority().equals("store")) {
                currentUser = (Store) appUserService.getUserByEmail(authUser.getUsername());
            } else if (authority.getAuthority().equals("client")) {
                currentUser = (Client) appUserService.getUserByEmail(authUser.getUsername());
            }

        }

        HttpSession session = request.getSession();
        session.setAttribute("currentUser", currentUser);

        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                        .getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    @Override
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
