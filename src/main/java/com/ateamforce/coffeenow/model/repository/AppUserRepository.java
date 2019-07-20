/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    
    AppUser findByEmail(@Param("email") String email);
    
    AppUser findAppUserById(@Param("appUserId")int appUserId);
    
}
