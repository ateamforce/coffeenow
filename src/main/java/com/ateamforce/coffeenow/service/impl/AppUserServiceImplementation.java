/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.service.AppUserService;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class AppUserServiceImplementation implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public void addAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUser(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

}
