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
import com.ateamforce.coffeenow.model.Store;
import com.ateamforce.coffeenow.model.repository.AppUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private PasswordEncoder passwordEncoder;

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

}
