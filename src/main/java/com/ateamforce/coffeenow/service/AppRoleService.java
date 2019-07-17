/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.AppRole;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface AppRoleService {

    void addAppRole(AppRole appRole);

    void deleteAppRole(AppRole appRole);
    
    void updateAppRole(AppRole appRole);

    List<AppRole> getAllAppRoles();

}
