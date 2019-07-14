/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.service.ExtraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/extras")
public class AdminExtrasController {

    @Autowired
    ExtraService extraService;

    @PostMapping("/add")
    public String admin_dashboard_extras_addExtra(@RequestParam("newExtraJson") String newExtraJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Extra newExtra = mapper.readValue(newExtraJson, Extra.class);
        extraService.addExtra(newExtra);
        return "redirect:/administrator/dashboard/extras";
    }

    @GetMapping("/delete/{extraId}")
    public String admin_dashboard_extras_deleteExtra(@PathVariable int extraId) {
        extraService.deleteExtraById(extraId);
        return "redirect:/administrator/dashboard/extras";
    }

    @PostMapping("/update")
    public String admin_dashboard_extras_updateExtra(@RequestParam("updatedExtraJson") String updatedExtraJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Extra updatedExtra = mapper.readValue(updatedExtraJson, Extra.class);
        extraService.updateExtra(updatedExtra);
        return "redirect:/administrator/dashboard/extras";
    }
}
