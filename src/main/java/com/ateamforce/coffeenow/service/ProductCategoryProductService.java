/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.ProductCategoryProduct;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ProductCategoryProductService {

    void addAllProductToProductsCategory(List<ProductCategoryProduct> productCategoryProduct);

    List<ProductCategoryProduct> getAllByProductCategoryid(int productcategoryid);

    List<ProductCategoryProduct> getAllByProductid(int productid);

    public void deleteAllGivenProductCategoryProducts(List<ProductCategoryProduct> productCategoryProduct);

}
