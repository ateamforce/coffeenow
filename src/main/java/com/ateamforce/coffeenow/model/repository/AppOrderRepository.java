/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.AppOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface AppOrderRepository extends JpaRepository<AppOrder,Integer> {
    
    AppOrder findById(@Param("id") int id);
    
    List<AppOrder> findByStore(@Param("storeid") int storeid);
    
    List<AppOrder> findByClient(@Param("clientid") int clientid);
    
}
