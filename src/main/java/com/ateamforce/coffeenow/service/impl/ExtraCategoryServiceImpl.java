/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.repository.ExtraCategoryRepository;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexa
 */
@Service
@Transactional
public class ExtraCategoryServiceImpl implements ExtraCategoryService {

    @Autowired
    ExtraCategoryRepository extraCategoryRepository;

    @Autowired
    ImageHandlerService imageHandlerService;

    @Autowired
    Environment env;

    @Override
    public ExtraCategory addExtraCategory(ExtraCategory extraCategory) {

        boolean hasChanged = false;
        ExtraCategory persistedExtraCategory = extraCategoryRepository.save(extraCategory);

        if (!extraCategory.getImage().isEmpty()) {
            persistedExtraCategory.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.extras.categories"),
                            persistedExtraCategory.getId(),
                            extraCategory
                    )
            );
            hasChanged = true;
        }

        if (persistedExtraCategory.getParent() == 0) {
            persistedExtraCategory.setParent(persistedExtraCategory.getId());
            hasChanged = true;
        }

        return (hasChanged) ? extraCategoryRepository.save(persistedExtraCategory) : persistedExtraCategory;
    }

    @Override
    public void deleteExtraCategoryById(int extraCategoryId) {

        boolean hasImage = getExtraCategoryById(extraCategoryId).isHasimage();
        extraCategoryRepository.deleteById(extraCategoryId);
        if (hasImage && getExtraCategoryById(extraCategoryId) == null) {
            imageHandlerService.deleteImage(env.getProperty("front.images.extras.categories"), extraCategoryId);
        }

    }

    @Override
    public List<ExtraCategory> getAllExtraCategories() {
        List<ExtraCategory> allExtraCategories = extraCategoryRepository.findAllExtraCategories();
        return allExtraCategories;
    }

    @Override
    public ExtraCategory getExtraCategoryById(int categoryId) {
        return extraCategoryRepository.findExtraCategoryById(categoryId);
    }

    @Override
    public List<ExtraCategory> getRemainigExtraCategoriesByExtraId(int extraId) {
        return extraCategoryRepository.findRemainigExtraCategoriesByExtraId(extraId);
    }

    @Override
    public List<ExtraCategory> getAllExtraCategoriesByProductCategoryId(int productCategoryId) {
        return extraCategoryRepository.findAllExtraCategoriesByProductCategoryId(productCategoryId);
    }

}
