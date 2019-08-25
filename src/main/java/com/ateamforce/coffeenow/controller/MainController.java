package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.model.Administrator;
import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequestMapping("/")
@ControllerAdvice
public class MainController {
    
    /**
     * Handles 404 requests
     * 
     * @param request
     * @return 
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(HttpServletRequest request) {

        String path = "front/404";
       
        if ( request.getRequestURL().indexOf("administrator/dashboard") != -1 ) {
            if ( SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("admin")) ) 
                path = "back_admin/dashboard/404";
        }
        else if ( request.getRequestURL().indexOf("store/dashboard") != -1 ) {
            if ( SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("store")) ) 
                path = "back_store/dashboard/404";
        }

        return path;
    }

    // Home Page
    @RequestMapping
    public String home(ModelMap modelmap) {
        return "front/index";
    }

    // Administrator Backend Login Page
    @RequestMapping("/administrator")
    public String admin_login(ModelMap modelmap) {
        return "back_admin/index";
    }

    // Store Backend Login Page
    @RequestMapping("/store")
    public String store_login(ModelMap modelmap) {
        return "back_store/index";
    }

    // TODO: FOR TESTING PURPOSES - TO BE REMOVED
    @Autowired
    AppUserService ausimp;

    @RequestMapping("/add")
    public String add() {
        Administrator appUser = new Administrator();
        appUser.setEmail("sakellariou23@hotmail.com");
        appUser.setPassword("123");
        AppRole role = new AppRole();
        role.setApprole("admin");
        appUser.setApprole(role);
        appUser.setName("akis");

//        appUser.setAddress("adadada");
//        appUser.setLongitude(1212154.45);
//        appUser.setLatitude(84.545445);
//        appUser.setPhone("12254");
//        appUser.setVat(45454544);
//        appUser.setStorename("aek");
//        appUser.setLogo("dadad");
//        appUser.setContactname("dadada");
//        appUser.setState("adada");
//        appUser.setZip(145);
        ausimp.addAppUser(appUser);
        return "front/login";
    }
    /////////////////////////////////////////////////////////

}
