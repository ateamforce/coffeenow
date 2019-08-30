/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.model.PaymentType;
import com.ateamforce.coffeenow.service.AppRoleService;
import com.ateamforce.coffeenow.service.PaymentTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard")
public class AdminController {

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    PaymentTypeService paymentTypeService;

    /**
     * Go to administrator dashboard page
     * 
     * @param modelmap
     * @return 
     */
    @GetMapping
    public String admin_dashboard(
            ModelMap modelmap
    ) {

        modelmap.addAttribute("appRoles", appRoleService.getAllAppRoles());
        modelmap.addAttribute("paymentTypes", paymentTypeService.getAllPaymentTypes());

        // add variable to indicate active sidebar
        modelmap.addAttribute("dashboardIsActive", "active");

        return "back_admin/dashboard/index";
    }

    @PostMapping("/addapprole")
    public String admin_dashboard_addAppRole(@RequestParam("newAppRoleJson") String newAppRoleJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AppRole newAppRole = mapper.readValue(newAppRoleJson, AppRole.class);
        appRoleService.addAppRole(newAppRole);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/deleteapprole")
    public String admin_dashboard_deleteAppRole(@RequestParam("appRoleJson") String appRoleJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AppRole appRole = mapper.readValue(appRoleJson, AppRole.class);
        appRoleService.deleteAppRole(appRole);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/updateapprole")
    public String admin_dashboard_updateAppRole(@RequestParam("updatedAppRoleJson") String updatedAppRoleJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        AppRole updatedAppRole = mapper.readValue(updatedAppRoleJson, AppRole.class);
        appRoleService.updateAppRole(updatedAppRole);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/addpaymenttype")
    public String admin_dashboard_addPaymentType(@RequestParam("newPaymentTypeJson") String newPaymentTypeJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentType newPaymentType = mapper.readValue(newPaymentTypeJson, PaymentType.class);
        paymentTypeService.addPaymentType(newPaymentType);
        return "redirect:/administrator/dashboard";
    }

    @GetMapping("/deletepaymenttype/{paymenttypeId}")
    public String admin_dashboard_deletePaymentType(@PathVariable int paymenttypeId) {
        paymentTypeService.deletePaymentTypeById(paymenttypeId);
        return "redirect:/administrator/dashboard";
    }

    @PostMapping("/updatepaymenttype")
    public String admin_dashboard_products_updateProduct(@RequestParam("updatedPaymentTypeJson") String updatedPaymentTypeJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentType updatedPaymentType = mapper.readValue(updatedPaymentTypeJson, PaymentType.class);
        paymentTypeService.updatePaymentType(updatedPaymentType);
        return "redirect:/administrator/dashboard";
    }

}
