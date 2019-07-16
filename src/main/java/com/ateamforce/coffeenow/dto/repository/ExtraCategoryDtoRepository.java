/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.repository;

import com.ateamforce.coffeenow.dto.ExtraCategoryDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface ExtraCategoryDtoRepository extends JpaRepository<ExtraCategoryDto,Integer> {
    
    List<ExtraCategoryDto> findAllExtraCategories();
    
    ExtraCategoryDto findExtraCategoryById(int categoryId);
    
     @Query(value = "SELECT*from extrascategories ec where id not in(select categoryid from extrascategories_extras where extraid=?1)",
            nativeQuery = true)
    public List<ExtraCategoryDto> findRemainigExtraCategoriesByExtraId(int extraId);
    
     @Query(value = "SELECT*from extrascategories ec where id in(select categoryid from extrascategories_extras where extraid=?1)",
            nativeQuery = true)
    public List<ExtraCategoryDto> findAllExtraCategoriesByExtraId(int extraId);
}
