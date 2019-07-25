/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.AppRole;
import com.ateamforce.coffeenow.model.repository.AppRoleRepository;
import com.ateamforce.coffeenow.service.AppRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
@Transactional
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public void addAppRole(AppRole appRole) {
        appRoleRepository.save(appRole);
    }

    @Override
    public void deleteAppRole(AppRole appRole) {
        appRoleRepository.delete(appRole);
    }

    @Override
    public void updateAppRole(AppRole appRole) {
        appRoleRepository.save(appRole);
    }

    @Override
    public List<AppRole> getAllAppRoles() {
        return appRoleRepository.findAllAppRoles();
    }

}
