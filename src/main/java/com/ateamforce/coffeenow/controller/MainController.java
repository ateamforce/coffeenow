package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.model.Administrator;
import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.service.impl.AppUserServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
    // Store Backend Registration form Page
    @RequestMapping(value = "/store/register", method = RequestMethod.GET)
    public String store_register_form(ModelMap modelmap, @ModelAttribute("newStore") NewStoreDto newStore) {
        return "back_store/register";
    }
    
    // Store Backend register parse
    @RequestMapping(value = "/store/register", method = RequestMethod.POST)
    public String store_register_parse(
        ModelMap modelmap, 
        @ModelAttribute("newStore") @Valid NewStoreDto newStore, 
        BindingResult result
    ) {
        
        if (result.hasErrors()) {
            return "back_store/register";
        }
        
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        
        return "redirect:/store/dashboard";
    }
    
    // TODO: FOR TESTING PURPOSES - TO BE REMOVED
    @Autowired
    AppUserServiceImpl ausimp;
    
    @RequestMapping("/add")
    public String add(){
        Administrator appUser=new Administrator();
        appUser.setEmail("sakellariou23@hotmail.com");
        appUser.setPassword("123");
        AppRole role=new AppRole();
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
    public String addstore(){
        Store appUser=new Store();
        appUser.setEmail("store@gmail.com");
        appUser.setPassword("123");
        AppRole role=new AppRole();
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
