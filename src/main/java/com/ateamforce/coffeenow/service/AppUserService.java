/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.TokenPasswordReset;
import com.ateamforce.coffeenow.model.TokenVerification;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public interface AppUserService  {

    AppUser addAppUser(AppUser appUser);
    
    void changeAppUserPassword(AppUser appUser, String password);

    void deleteAppUser(AppUser appUser);

    AppUser getUserByEmail(String email);

    AppUser findAppUserById(int appUserId);

    AppUser registerNewStore(NewStoreDto newStore);
    
    // verification token
    AppUser getAppUserByVerificationToken(String token);
    TokenVerification createVerificationTokenForAppUser(AppUser user, String token);
    TokenVerification getVerificationToken(String token);
    TokenVerification generateNewVerificationToken(TokenVerification token);
    String validateVerificationToken(String token);
    TokenVerification getVerificationTokenByAppUser(AppUser user);
    
    // password reset token
    AppUser getAppUserByPasswordResetToken(String token);
    TokenPasswordReset createPasswordResetTokenForAppUser(AppUser user, String token);
    TokenPasswordReset getPasswordResetToken(String token);
    TokenPasswordReset generateNewPasswordResetToken(TokenPasswordReset token);
    String validatePasswordResetToken(long id, String token);
    TokenPasswordReset getPasswordResetTokenByAppUser(AppUser user);
    void deletePasswordResetToken(TokenPasswordReset token);
    void deletePasswordResetToken(String token);
    
}
