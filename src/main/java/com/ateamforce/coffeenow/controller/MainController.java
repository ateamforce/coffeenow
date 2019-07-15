package com.ateamforce.coffeenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

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

    // TODO : Move this to the store controller, when it is created (like in AdministratorController)
    @RequestMapping("/store/dashboard")
    public String store_dashboard(ModelMap modelmap) {
        return "back_store/dashboard/index";
    }

}
