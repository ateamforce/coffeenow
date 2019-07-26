/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service;

import com.ateamforce.coffeenow.model.ExtraCategory;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ExtraCategoryService {

    ExtraCategory addExtraCategory(ExtraCategory extraCategory);

    void deleteExtraCategoryById(int extraCategoryId);

    List<ExtraCategory> getAllExtraCategories();

    ExtraCategory getExtraCategoryById(int categoryId);

    List<ExtraCategory> getRemainigExtraCategoriesByExtraId(int extraId);

    public List<ExtraCategory> getAllExtraCategoriesByProductCategoryId(int productCategoryId);

    public void addExtrasToExtraCategory(ExtraCategory extraCategory);

    public void addProductsCategoriesToExtraCategory(ExtraCategory extraCategory);
    
}
