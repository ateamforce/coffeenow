/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.ExtrasCategoryProductsCategory;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ExtrasCategoryProductsCategoryService {

    void addAllExtrasCategoryProductsCategory(List<ExtrasCategoryProductsCategory> extrasCategoryProductsCategory);

    List<ExtrasCategoryProductsCategory> getAllByExtracategoryid(int extracategoryid);
    
    List<ExtrasCategoryProductsCategory> getAllByProductcategoryid(int productcategoryid);

    public void deleteAllGivenExtrasCategoriesProductsCategories(List<ExtrasCategoryProductsCategory> extrasCategoriesProductsCategories);
}
