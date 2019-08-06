/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.interceptor;

import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.service.AppUserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author alexandros
 */
@Component
public class CurrentUserInterceptor implements HandlerInterceptor {

    @Autowired
    AppUserService appUserService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        
        try {
            String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AppUser currentUser = appUserService.getUserByEmail(username);
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", currentUser);

        } catch (NullPointerException e) {
            System.out.println("no currentuser");
        }

    }

}
