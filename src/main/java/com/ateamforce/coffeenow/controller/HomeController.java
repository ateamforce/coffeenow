package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.model.Administrator;
import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.model.AppUser;

import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.service.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    // No spring security locking needed
    @RequestMapping
    public String home(Model model) {
        return "front/login";
    }

    // No spring security locking needed
    @RequestMapping("/administrator")
    public String admin_login() {
            return "back_admin/login";
    }

    // secure this
    @RequestMapping("/administrator/dashboard")
    public String admin_dashboard() {
            return "back_admin/dashboard/index";
    }

    // No spring security locking needed
    @RequestMapping("/store")
    public String store_login() {
            return "back_store/login";
    }

    // secure this
    @RequestMapping("/store/dashboard")
    public String store_dashboard() {
            return "back_store/dashboard/index";
    }
    
    @Autowired
    AppUserServiceImpl ausimp;
    
    @RequestMapping("/add")
    public String add(){
        Administrator appUser=new Administrator();
        appUser.setEmail("aaa");
        appUser.setPassword("123");
        AppRole role=new AppRole();
        role.setApprole("admin");
        appUser.setApprole(role);
        appUser.setName("alepo");
        
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

}