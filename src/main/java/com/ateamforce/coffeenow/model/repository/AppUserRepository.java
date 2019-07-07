/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alexa
 */
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    
    
    
}
