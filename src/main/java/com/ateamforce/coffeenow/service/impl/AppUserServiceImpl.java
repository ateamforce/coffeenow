/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.exception.UserAlreadyExistException;
import com.ateamforce.coffeenow.service.AppUserService;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.TokenVerification;
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.TokenPasswordReset;
import com.ateamforce.coffeenow.model.repository.AppUserRepository;
import com.ateamforce.coffeenow.model.repository.TokenPasswordResetRepository;
import java.util.Calendar;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ateamforce.coffeenow.model.repository.TokenVerificationRepository;

/**
 *
 * @author alexa
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;
    
    @Autowired
    private TokenVerificationRepository tokenVerificationRepository;
    
    @Autowired
    private TokenPasswordResetRepository tokenPasswordResetRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    @Override
    public AppUser addAppUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }
    
    @Override
    public void changeAppUserPassword(AppUser appUser, String password) {
        appUser.setPassword(passwordEncoder.encode(password));
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

    @Override
    public AppUser findAppUserById(int appUserId) {
        return appUserRepository.findAppUserById(appUserId);
    }

    @Transactional
    @Override
    public AppUser registerNewStore(NewStoreDto newStore) {
        if (emailExists(newStore.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + newStore.getEmail());
        }

        final Store store = new Store();

        store.setEmail(newStore.getEmail());
        store.setPassword(newStore.getPassword());
        store.setAddress(newStore.getAddress());
        store.setPhone(newStore.getPhone());
        store.setVat(newStore.getVat());
        store.setStorename(newStore.getStorename());
        store.setContactname(newStore.getContactname());
        store.setState(newStore.getState());
        store.setZip(newStore.getZip());

        return addAppUser(store);

    }

    private boolean emailExists(final String email) {
        return appUserRepository.findByEmail(email) != null;
    }
    
    // verification token
    
    @Override
    public AppUser getAppUserByVerificationToken(String token) {
        final TokenVerification verificationToken = tokenVerificationRepository.findByToken(token);
        if (verificationToken != null) {
            return verificationToken.getUser();
        }
        return null;
    }

    @Override
    public TokenVerification createVerificationTokenForAppUser(AppUser user, String token) {
        final TokenVerification myToken = new TokenVerification(token, user);
        return tokenVerificationRepository.save(myToken);
    }

    @Override
    public TokenVerification getVerificationToken(String verificationToken) {
        return tokenVerificationRepository.findByToken(verificationToken);
    }

    @Override
    public TokenVerification generateNewVerificationToken(TokenVerification token) {
        token.updateToken(UUID.randomUUID().toString());
        return tokenVerificationRepository.save(token);
    }

    @Override
    public String validateVerificationToken(String token) {
        final TokenVerification verificationToken = tokenVerificationRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final AppUser user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
            .getTime()
            - cal.getTime()
                .getTime()) <= -4) {
            tokenVerificationRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        tokenVerificationRepository.delete(verificationToken);
        appUserRepository.save(user);
        return TOKEN_VALID;
    }
    
    @Override
    public TokenVerification getVerificationTokenByAppUser(AppUser user){
        final TokenVerification verificationToken = tokenVerificationRepository.findByUser(user);
        if (verificationToken != null) {
            return verificationToken;
        }
        return null;
    }
    
    // password reset token

    @Override
    public AppUser getAppUserByPasswordResetToken(String token) {
        final TokenPasswordReset passwordResetToken = tokenPasswordResetRepository.findByToken(token);
        if (passwordResetToken != null) {
            return passwordResetToken.getUser();
        }
        return null;
    }

    @Override
    public TokenPasswordReset createPasswordResetTokenForAppUser(AppUser user, String token) {
        final TokenPasswordReset myToken = new TokenPasswordReset(token, user);
        return tokenPasswordResetRepository.save(myToken);
    }

    @Override
    public TokenPasswordReset getPasswordResetToken(String token) {
        return tokenPasswordResetRepository.findByToken(token);
    }

    @Override
    public TokenPasswordReset generateNewPasswordResetToken(TokenPasswordReset token) {
        token.updateToken(UUID.randomUUID().toString());
        return tokenPasswordResetRepository.save(token);
    }
    
    @Override
    public String validatePasswordResetToken(long id, String token) {
        final TokenPasswordReset passwordResetToken = tokenPasswordResetRepository.findByToken(token);
        if ((passwordResetToken == null) || (passwordResetToken.getUser().getId() != id)) {
            return TOKEN_INVALID;
        }

        final Calendar cal = Calendar.getInstance();
        if ((passwordResetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= -4) {
            tokenPasswordResetRepository.delete(passwordResetToken);
            return TOKEN_EXPIRED;
        }
        
        return TOKEN_VALID;
    }

    @Override
    public TokenPasswordReset getPasswordResetTokenByAppUser(AppUser user) {
        final TokenPasswordReset passwordResetToken = tokenPasswordResetRepository.findByUser(user);
        if (passwordResetToken != null) {
            return passwordResetToken;
        }
        return null;
    }

    @Override
    public void deletePasswordResetToken(TokenPasswordReset token) {
        tokenPasswordResetRepository.delete(token);
    }

    @Override
    public void deletePasswordResetToken(String token) {
        tokenPasswordResetRepository.delete(tokenPasswordResetRepository.findByToken(token));
    }

}
