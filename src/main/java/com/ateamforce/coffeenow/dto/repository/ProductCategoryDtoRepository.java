/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.repository;

import com.ateamforce.coffeenow.dto.ProductCategoryDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexa
 */
@Repository
public interface ProductCategoryDtoRepository extends JpaRepository<ProductCategoryDto,Integer> {
    
    List<ProductCategoryDto> findAllProductCategories();

    ProductCategoryDto findProductCategoryById(int categoryId);
    
    @Query(value = "SELECT*from productcategories pc where id not in(select categoryid from productcategories_products where productid=?1)",
            nativeQuery = true)
    public List<ProductCategoryDto> findRemainigProductCategoriesByProductId(int productId);
    
    @Query(value = "SELECT*from productcategories pc where id in(select categoryid from productcategories_products where productid=?1)",
            nativeQuery = true)
    public List<ProductCategoryDto> findAllProductCategoriesByProductId(int productId);
    
    @Query(value = "SELECT*from productcategories pc where id in(select productcategoryid from extras_products where extracategoryid=?1)",
            nativeQuery = true)
    public List<ProductCategoryDto> findAllProductCategoriesByExtraCategoryId(int extracategoryId);
    
    @Query(value = "SELECT*from productcategories pc where id not in(select productcategoryid from extras_products where extracategoryid=?1)",
            nativeQuery = true)
    public List<ProductCategoryDto> findRemainigProductCategoriesByExtraCategoryId(int extracategoryId);
}
