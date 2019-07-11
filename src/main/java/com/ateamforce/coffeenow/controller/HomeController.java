package com.ateamforce.coffeenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    // No spring security locking needed
    @RequestMapping
    public String home(Model model) {
        return "front/index";
    }

    // No spring security locking needed
    @RequestMapping("/administrator")
    public String admin_login() {
            return "back_admin/index";
    }

    // secure this
    @RequestMapping("/administrator/dashboard")
    public String admin_dashboard() {
            return "back_admin/dashboard/index";
    }

    // No spring security locking needed
    @RequestMapping("/store")
    public String store_login() {
            return "back_store/index";
    }

    // secure this
    @RequestMapping("/store/dashboard")
    public String store_dashboard() {
            return "back_store/dashboard/index";
    }

}