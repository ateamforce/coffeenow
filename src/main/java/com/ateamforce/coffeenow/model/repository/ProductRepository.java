/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model.repository;

import com.ateamforce.coffeenow.model.Product;
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
public interface ProductRepository extends JpaRepository<Product,Integer> {
    
    List<Product>findAllProducts();
    
    Product findProductById(@Param("productId")int productId);
    
    @Query(value = "SELECT*from products p where id not in(select productid from productcategories_products where categoryid=?1)",
            nativeQuery = true)
    public List<Product> findRemainigProductsByProductCategoryId(int categoryid);
    
    @Query(value = "SELECT*from products p where id in(select productid from productcategories_products where categoryid=?1)",
            nativeQuery = true)
    public List<Product> findAllProductsByProductCategoryId(int categoryid);
    
}
