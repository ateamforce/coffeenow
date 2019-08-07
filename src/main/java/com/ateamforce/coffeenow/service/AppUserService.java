/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.AppUserToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public interface AppUserService  {

    AppUser addAppUser(AppUser appUser);

    void deleteAppUser(AppUser appUser);

    AppUser getUserByEmail(String email);
    
    AppUser getUserByToken(String appUserToken);

    AppUser findAppUserById(int appUserId);

    AppUser registerNewStore(NewStoreDto newStore);
    
    void createTokenForAppUser(AppUser user, String token);
    
    AppUserToken getAppUserToken(String VerificationToken);

    AppUserToken generateNewAppUserToken(String token);
    
    String validateAppUserToken(String token);
}
