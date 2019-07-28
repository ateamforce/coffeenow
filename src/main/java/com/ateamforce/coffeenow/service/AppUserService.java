/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author alexa
 */
public interface AppUserService extends UserDetailsService {
    
    void addAppUser(AppUser appUser);
    
    void deleteAppUser(AppUser appUser);
    
    AppUser getUserByEmail(String email);
    
    @Override
    UserDetails loadUserByUsername(String email);
    
    AppUser findAppUserById(int appUserId);
}
