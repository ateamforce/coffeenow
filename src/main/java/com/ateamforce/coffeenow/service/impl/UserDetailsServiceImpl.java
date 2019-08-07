/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.service.AppUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexandros
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    AppUserService appUserService;
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Get the User
        AppUser appuser = appUserService.getUserByEmail(email);

        if (appuser == null || !appuser.isEnabled()) {
            throw new UsernameNotFoundException(email);
        }

        //Add all roles to grantList
        String roleName = appuser.getApprole().getApprole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        grantList.add(authority);

        UserDetails userDetails = (UserDetails) new User(
                appuser.getEmail(),
                appuser.getPassword(),
                grantList
        );

        return userDetails;
    }
}
