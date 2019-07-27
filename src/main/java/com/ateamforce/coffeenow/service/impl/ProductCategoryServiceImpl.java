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
@Transactional
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

        if (productCategory.getExtrascategoriesList() != null) {
            addExtrasCategoriesToProductCategory(productCategory);
        }

        if (productCategory.getProductsList() != null) {
            addPoductsToProductCategory(productCategory);
        }

        return (hasChanged) ? productCategoryRepository.save(persistedProductCategory) : persistedProductCategory;
    }

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

    @Override
    public List<ProductCategory> getAllProductCategories() {
        List<ProductCategory> allProductCategories = productCategoryRepository.findAllProductCategories();
        return allProductCategories;
    }

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

    @Override
    public void addExtrasCategoriesToProductCategory(ProductCategory productCategory) {
        int productCategoryId = productCategory.getId();
        try {
            extrasCategoryProductsCategoryService
                    .deleteAllGivenExtrasCategoriesProductsCategories(extrasCategoryProductsCategoryService
                            .getAllByProductcategoryid(productCategoryId));
        } catch (NullPointerException e) {
            LOGGER.error("No Extra Categories Found");
        } finally {
            List<ExtraCategory> extraCategoriesList = productCategory.getExtrascategoriesList();
            List<ExtrasCategoryProductsCategory> extrasCategoryProductsCategoryList = new ArrayList();
            extraCategoriesList.forEach((extraCategory) -> {
                extrasCategoryProductsCategoryList
                        .add(new ExtrasCategoryProductsCategory(extraCategory.getId(), productCategoryId));
            });
            extrasCategoryProductsCategoryService
                    .addAllExtrasCategoryProductsCategory(extrasCategoryProductsCategoryList);
        }
    }

    @Override
    public void addPoductsToProductCategory(ProductCategory productCategory) {
        int productCategoryId = productCategory.getId();
        try {
            productCategoryProductService
                    .deleteAllGivenProductCategoryProducts(productCategoryProductService
                            .getAllByProductCategoryid(productCategoryId));
        } catch (NullPointerException e) {
            LOGGER.error("No Products Found");
        } finally {
            List<Product> productsList = productCategory.getProductsList();
            List<ProductCategoryProduct> productcategoriesProductsList = new ArrayList();
            for (Product product : productsList) {
                productcategoriesProductsList.add(new ProductCategoryProduct(productCategoryId, product.getId()));
            }
            productCategoryProductService
                    .addAllProductToProductsCategory(productcategoriesProductsList);
        }
    }

}
