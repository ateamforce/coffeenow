/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.model.repository.ProductCategoryRepository;
import com.ateamforce.coffeenow.service.ProductCategoryService;
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
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final static Logger LOGGER = Logger.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ImageHandlerService imageHandlerService;

    @Autowired
    Environment env;

    @Transactional
    @Override
    public ProductCategory addProductCategory(ProductCategory productCategory) {

        boolean hasChanged = false;
        ProductCategory persistedProductCategory = productCategoryRepository.save(productCategory);

        if (!productCategory.getImage().isEmpty()) {
            persistedProductCategory.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.products.categories"),
                            persistedProductCategory.getId(),
                            productCategory
                    )
            );
            hasChanged = true;
        }

        if (persistedProductCategory.getParent() == 0) {
            persistedProductCategory.setParent(persistedProductCategory.getId());
            hasChanged = true;
        }

        return (hasChanged) ? productCategoryRepository.save(persistedProductCategory) : persistedProductCategory;
    }

    @Transactional
    @Override
    public void deleteProductCategoryById(int productCategoryId) {

        boolean hasImage = getProductCategoryById(productCategoryId).isHasimage();
        productCategoryRepository.deleteById(productCategoryId);
        if (hasImage && getProductCategoryById(productCategoryId) == null) {
            imageHandlerService.deleteImage(env.getProperty("front.images.products.categories"), productCategoryId);
        }

    }

    @Transactional
    @Override
    public List<ProductCategory> getAllProductCategories() {
        List<ProductCategory> allProductCategories = productCategoryRepository.findAllProductCategories();
        allProductCategories.forEach((e) -> {
            e.getExtrascategoriesList().size(); // force JPA to prefetch these ( works in combination with the @Transactional )
            e.getProductsList().size(); // force JPA to prefetch these as well
        });
        return allProductCategories;
    }

    @Transactional
    @Override
    public ProductCategory getProductCategoryById(int categoryId) {
        ProductCategory productCategory = productCategoryRepository.findProductCategoryById(categoryId);
        productCategory.getExtrascategoriesList().size();// force JPA to prefetch these
        productCategory.getProductsList().size();// force JPA to prefetch these
        return productCategory;
    }

    @Override
    public List<ProductCategory> getRemainigProductCategoriesByProductId(int productId) {
        return productCategoryRepository.findRemainigProductCategoriesByProductId(productId);
    }

    @Override
    public List<ProductCategory> getAllProductCategoriesByExtraCategoryId(int extraCategoryId) {
        return productCategoryRepository.findAllProductCategoriesByExtraCategoryId(extraCategoryId);
    }

}
