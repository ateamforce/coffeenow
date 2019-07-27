/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.ProductCategory;
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
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findAllProductCategories();
    
    ProductCategory findProductCategoryById(@Param("categoryId")int categoryId);
    
    @Query(value = "SELECT*from productcategories pc where id not in(select categoryid from productcategories_products where productid=?1)",
            nativeQuery = true)
    public List<ProductCategory> findRemainigProductCategoriesByProductId(int productId);
    
    @Query(value = "SELECT*from productcategories pc where id in(select categoryid from productcategories_products where productid=?1)",
            nativeQuery = true)
    public List<ProductCategory> findAllProductCategoriesByProductId(int productId);
    
    @Query(value = "SELECT * FROM productcategories where id in (SELECT productcategoryid from extras_products where extracategoryid=?1)",
            nativeQuery = true)
    public List<ProductCategory> findAllProductCategoriesByExtraCategoryId(int extraCategoryId);
}
