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
 * @author alexa
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    
    @Autowired
    AppUserService appUserService;
    
    
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Get the User
        AppUser appuser=this.appUserService.getUserByEmail(email);
        System.out.println(appuser);
        
        
        if (appuser == null) {
            System.out.println("User not found! " + email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        
        //Add all roles to grantList
        String roleName=appuser.getRole();
        
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

                GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                grantList.add(authority);

        
        UserDetails userDetails = (UserDetails) new User(appuser.getEmail(),
                appuser.getPassword(), grantList);

        return userDetails;
        
    }
    
}
