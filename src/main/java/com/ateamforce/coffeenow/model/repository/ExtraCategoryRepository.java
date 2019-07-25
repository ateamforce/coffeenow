/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.ExtraCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface ExtraCategoryRepository extends JpaRepository<ExtraCategory,Integer> {
    
    List<ExtraCategory> findAllExtraCategories();
    
    ExtraCategory findExtraCategoryById(@Param("categoryId")int categoryId);
    
     @Query(value = "SELECT*from extrascategories ec where id not in(select categoryid from extrascategories_extras where extraid=?1)",
            nativeQuery = true)
    public List<ExtraCategory> findRemainigExtraCategoriesByExtraId(int extraId);
    
    @Query(value = "SELECT*from extrascategories ec where id in(select extracategoryid from extras_products where productcategoryid=?1)",
            nativeQuery = true)
    public List<ExtraCategory> findAllExtraCategoriesByProductCategoryId(int productCategoryId);
    
}
