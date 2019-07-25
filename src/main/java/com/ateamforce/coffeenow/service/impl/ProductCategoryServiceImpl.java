/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.ExtrasCategoryProductsCategory;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.model.ProductCategoryProduct;
import com.ateamforce.coffeenow.model.repository.ProductCategoryRepository;
import com.ateamforce.coffeenow.service.ExtrasCategoryProductsCategoryService;
import com.ateamforce.coffeenow.service.ProductCategoryProductService;
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
    ExtrasCategoryProductsCategoryService extrasCategoryProductsCategoryService;

    @Autowired
    ProductCategoryProductService productCategoryProductService;

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

        List<ExtraCategory> extraCategoriesList = productCategory.getExtrascategoriesList();
        LOGGER.error("productCategory.getExtrascategoriesList() : " + extraCategoriesList);
        if (productCategory.getExtrascategoriesList() != null && !extraCategoriesList.isEmpty()) {
            addExtraCategoriesToProductCategory(productCategory);
        }

        List<Product> productsList = productCategory.getProductsList();
        LOGGER.error("productCategory.getProductsList() : " + productsList);
        if (productCategory.getProductsList() != null && !productsList.isEmpty()) {
            addPoductsToProductCategory(productCategory);
        }

        return (hasChanged) ? productCategoryRepository.save(persistedProductCategory) : persistedProductCategory;
    }

    @Transactional
    @Override
    public void deleteProductCategoryById(int productCategoryId) {

        boolean hasImage = getProductCategoryById(productCategoryId).isHasimage();
        productCategoryRepository.deleteById(productCategoryId);
        try {
            getProductCategoryById(productCategoryId);
        } catch (NullPointerException e) {
            if (hasImage) {
                imageHandlerService.deleteImage(env.getProperty("front.images.products.categories"), productCategoryId);
            }
        }
    }

    @Transactional
    @Override
    public List<ProductCategory> getAllProductCategories() {
        List<ProductCategory> allProductCategories = productCategoryRepository.findAllProductCategories();
        return allProductCategories;
    }

    @Transactional
    @Override
    public ProductCategory getProductCategoryById(int categoryId) {
        return productCategoryRepository.findProductCategoryById(categoryId);
    }

    @Override
    public List<ProductCategory> getRemainigProductCategoriesByProductId(int productId) {
        return productCategoryRepository.findRemainigProductCategoriesByProductId(productId);
    }

    @Override
    public List<ProductCategory> getAllProductCategoriesByExtraCategoryId(int extraCategoryId) {
        return productCategoryRepository.findAllProductCategoriesByExtraCategoryId(extraCategoryId);
    }

    @Transactional
    @Override
    public void addExtraCategoriesToProductCategory(ProductCategory productCategory) {
        List<ExtraCategory> extraCategoriesList = productCategory.getExtrascategoriesList();
        int productCategoryId = productCategory.getId();
        List<ExtrasCategoryProductsCategory> extrasCategoryProductsCategoryList = new ArrayList();
        extraCategoriesList.forEach((extraCategory) -> {
            extrasCategoryProductsCategoryList
                    .add(new ExtrasCategoryProductsCategory(extraCategory.getId(), productCategoryId));
        });
        extrasCategoryProductsCategoryService
                .addAllExtrasToCategoryProductsCategory(extrasCategoryProductsCategoryList);
    }

    @Transactional
    @Override
    public void addPoductsToProductCategory(ProductCategory productCategory) {
        List<Product> productsList = productCategory.getProductsList();
        int productCategoryId = productCategory.getId();
        List<ProductCategoryProduct> productcategoriesProductsList = new ArrayList();
        for (Product product : productsList) {
            productcategoriesProductsList.add(new ProductCategoryProduct(productCategoryId, product.getId()));
        }
        productCategoryProductService
                .addAllProductToProductsCategory(productcategoriesProductsList);
    }

}
