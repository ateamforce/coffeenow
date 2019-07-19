/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.repository.ExtraCategoryRepository;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
public class ExtraCategoryServiceImpl implements ExtraCategoryService {

    @Autowired
    ExtraCategoryRepository extraCategoryRepository;

    @Transactional
    @Override
    public ExtraCategory addExtraCategory(ExtraCategory extraCategory) {
        ExtraCategory persistedExtraCategory = extraCategoryRepository.save(extraCategory);
        if ( persistedExtraCategory.getParent() == 0 ) {
            persistedExtraCategory.setParent(persistedExtraCategory.getId());
            return extraCategoryRepository.save(persistedExtraCategory);
        }
        return persistedExtraCategory;
    }

    @Override
    public void deleteExtraCategoryById(int extraCategoryId) {
        extraCategoryRepository.deleteById(extraCategoryId);
    }

    @Override
    public void updateExtraCategory(ExtraCategory updatedExtraCategory) {
        extraCategoryRepository.save(updatedExtraCategory);
    }

    @Override
    public List<ExtraCategory> getAllExtraCategories() {
        return extraCategoryRepository.findAllExtraCategories();
    }

    @Override
    public ExtraCategory getExtraCategoryById(int categoryId) {
        return extraCategoryRepository.findExtraCategoryById(categoryId);
    }

    @Override
    public List<ExtraCategory> getRemainigExtraCategoriesByExtraId(int extraId) {
        return extraCategoryRepository.findRemainigExtraCategoriesByExtraId(extraId);
    }

}
