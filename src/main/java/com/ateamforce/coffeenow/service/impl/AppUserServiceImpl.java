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
import com.ateamforce.coffeenow.model.AppUserToken;
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.repository.AppUserRepository;
import com.ateamforce.coffeenow.model.repository.AppUserTokenRepository;
import java.util.Calendar;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;
    
    @Autowired
    private AppUserTokenRepository tokenRepository;

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
        store.setPasswordRepeat(newStore.getPasswordRepeat());
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

    @Override
    public void createTokenForAppUser(AppUser user, String token) {
        final AppUserToken myToken = new AppUserToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public AppUserToken getAppUserToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Override
    public AppUserToken generateNewAppUserToken(String token) {
        AppUserToken vToken = tokenRepository.findByToken(token);
        vToken.updateToken(UUID.randomUUID()
            .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public String validateAppUserToken(String token) {
        final AppUserToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final AppUser user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
            .getTime()
            - cal.getTime()
                .getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        tokenRepository.delete(verificationToken);
        appUserRepository.save(user);
        return TOKEN_VALID;
    }

    @Override
    public AppUser getUserByToken(String appUserToken) {
        final AppUserToken token = tokenRepository.findByToken(appUserToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

}
