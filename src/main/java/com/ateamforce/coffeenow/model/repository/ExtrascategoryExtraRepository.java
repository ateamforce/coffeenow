/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.ExtrascategoryExtra;
import com.ateamforce.coffeenow.model.ExtrascategoryExtraPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface ExtrascategoryExtraRepository extends JpaRepository<ExtrascategoryExtra,ExtrascategoryExtraPK> {
    
    List<ExtrascategoryExtra>findByExtraCategoryid(@Param("extraCategoryId")int extraCategoryId);
    
    
}
