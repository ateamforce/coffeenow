package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.model.Administrator;
import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.repository.AppUserTokenRepository;
import com.ateamforce.coffeenow.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    private AppUserTokenRepository tokenRepository;

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
    public String store_login(ModelMap modelmap, @ModelAttribute("mainMessage") final String msg) {
        if (msg != null && !msg.isEmpty()) modelmap.addAttribute("mainMessage", msg);
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

    @RequestMapping("/addstore")
    public String addstore() {
        Store appUser = new Store();
        appUser.setEmail("store@gmail.com");
        appUser.setPassword("123");
        AppRole role = new AppRole();
        role.setApprole("store");
        appUser.setApprole(role);

        appUser.setAddress("adadada");
        appUser.setLongitude(1212154.45);
        appUser.setLatitude(84.545445);
        appUser.setPhone("12254");
        appUser.setVat(45454544);
        appUser.setStorename("aek");
        appUser.setContactname("dadada");
        appUser.setState("adada");
        appUser.setZip(145);
        ausimp.addAppUser(appUser);
        return "front/login";
    }
    /////////////////////////////////////////////////////////

}
