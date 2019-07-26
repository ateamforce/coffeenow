/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ExtrasCategoryProductsCategory;
import com.ateamforce.coffeenow.model.repository.ExtrasCategoryProductsCategoryRepository;
import com.ateamforce.coffeenow.service.ExtrasCategoryProductsCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ExtrasCategoryProductsCategoryServiceImpl implements ExtrasCategoryProductsCategoryService {

    @Autowired
    ExtrasCategoryProductsCategoryRepository extrasCategoryProductsCategoryRepository;

    @Override
    public void addAllExtrasCategoryProductsCategory(List<ExtrasCategoryProductsCategory> extrasCategoryProductsCategory) {
        extrasCategoryProductsCategoryRepository.saveAll(extrasCategoryProductsCategory);
    }

    @Override
    public List<ExtrasCategoryProductsCategory> getAllByExtracategoryid(int extracategoryid) {
        return extrasCategoryProductsCategoryRepository.findAllByExtracategoryid(extracategoryid);
    }

    @Override
    public List<ExtrasCategoryProductsCategory> getAllByProductcategoryid(int productcategoryid) {
        return extrasCategoryProductsCategoryRepository.findAllByProductcategoryid(productcategoryid);
    }

    @Override
    public void deleteAllGivenExtrasCategoriesProductsCategories(List<ExtrasCategoryProductsCategory> extrasCategoriesProductsCategories) {
        extrasCategoryProductsCategoryRepository.deleteAll(extrasCategoriesProductsCategories);
    }

}
