/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.ExtrasCategoryProductsCategory;
import com.ateamforce.coffeenow.model.ExtrascategoryExtra;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.model.repository.ExtraCategoryRepository;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtrasCategoryProductsCategoryService;
import com.ateamforce.coffeenow.service.ExtrascategoryExtraService;
import com.ateamforce.coffeenow.util.ImageHandlerService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
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

    private final static Logger LOGGER = Logger.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    ExtraCategoryRepository extraCategoryRepository;

    @Autowired
    ExtrascategoryExtraService extrascategoryExtraService;

    @Autowired
    ExtrasCategoryProductsCategoryService extrasCategoryProductsCategoryService;

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

        if (extraCategory.getExtrasList() != null) {
            addExtrasToExtraCategory(extraCategory);
        }

        if (extraCategory.getProductcategoriesList() != null) {
            addProductsCategoriesToExtraCategory(extraCategory);
        }

        return (hasChanged) ? extraCategoryRepository.save(persistedExtraCategory) : persistedExtraCategory;
    }

    @Override
    public void deleteExtraCategoryById(int extraCategoryId) {

        boolean hasImage = getExtraCategoryById(extraCategoryId).isHasimage();
        extraCategoryRepository.deleteById(extraCategoryId);

        if ((getExtraCategoryById(extraCategoryId)) == null && hasImage) {
            imageHandlerService.deleteImage(env
                    .getProperty("front.images.extras.categories"), extraCategoryId);

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
    public List<ExtraCategory> getAllExtraCategoriesByExtraId(int extraId) {
        return extraCategoryRepository.findAllExtraCategoriesByExtraId(extraId);
    }

    @Override
    public List<ExtraCategory> getRemainigExtraCategoriesByExtraId(int extraId) {
        return extraCategoryRepository.findRemainigExtraCategoriesByExtraId(extraId);
    }

    @Override
    public List<ExtraCategory> getAllExtraCategoriesByProductCategoryId(int productCategoryId) {
        return extraCategoryRepository.findAllExtraCategoriesByProductCategoryId(productCategoryId);
    }

    @Override
    public void addExtrasToExtraCategory(ExtraCategory extraCategory) {
        int extraCategoryId = extraCategory.getId();
        try {
            extrascategoryExtraService
                    .deleteAllGivenExtrascategoryExtras(extrascategoryExtraService
                            .getByExtraCategoryid(extraCategoryId));
        } catch (NullPointerException e) {
            LOGGER.error("No Extras Found");
        } finally {
            List<Extra> extrasList = extraCategory.getExtrasList();
            List<ExtrascategoryExtra> extrascategoryExtraList = new ArrayList();
            for (Extra extra : extrasList) {
                extrascategoryExtraList.add(new ExtrascategoryExtra(extraCategoryId, extra.getId()));
            }
            extrascategoryExtraService
                    .addAllExtrascategoryExtra(extrascategoryExtraList);
        }
    }

    @Override
    public void addProductsCategoriesToExtraCategory(ExtraCategory extraCategory) {
        int extraCategoryId = extraCategory.getId();
        try {

            extrasCategoryProductsCategoryService
                    .deleteAllGivenExtrasCategoriesProductsCategories(extrasCategoryProductsCategoryService
                            .getAllByExtracategoryid(extraCategoryId));
        } catch (NullPointerException e) {
            LOGGER.error("No Product Categories Found");
        } finally {
            List<ProductCategory> productCategoriesList = extraCategory.getProductcategoriesList();
            List<ExtrasCategoryProductsCategory> extrasCategoryProductsCategoryList = new ArrayList();
            for (ProductCategory productCategory : productCategoriesList) {
                extrasCategoryProductsCategoryList
                        .add(new ExtrasCategoryProductsCategory(extraCategoryId, productCategory.getId()));
            }
            extrasCategoryProductsCategoryService
                    .addAllExtrasCategoryProductsCategory(extrasCategoryProductsCategoryList);
        }

    }

}
