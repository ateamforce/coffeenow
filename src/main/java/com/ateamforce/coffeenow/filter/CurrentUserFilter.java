package com.ateamforce.coffeenow.filter;

import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.service.AppUserService;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Sakel
 */
public class CurrentUserFilter implements Filter {
    
    private AppUserService appUserService;

    @Override
    public void destroy() {
            // ...
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            //
    }

    @Override
    public void doFilter(ServletRequest request, 
           ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // nice way to get a bean without Autowired
        // https://stackoverflow.com/questions/32494398/unable-to-autowire-the-service-inside-my-authentication-filter-in-spring
        if(appUserService==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            appUserService = webApplicationContext.getBean(AppUserService.class);
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AppUser currentUser = appUserService.getUserByEmail(user.getUsername());
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("currentUser", currentUser);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        chain.doFilter(request, response);

    }

}