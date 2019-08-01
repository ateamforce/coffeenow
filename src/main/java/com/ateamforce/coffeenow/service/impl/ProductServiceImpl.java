/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.service.impl;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.model.ProductCategoryProduct;
import com.ateamforce.coffeenow.model.repository.ProductRepository;
import com.ateamforce.coffeenow.service.ProductCategoryProductService;
import com.ateamforce.coffeenow.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final static Logger LOGGER = Logger.getLogger(ProductCategoryServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageHandlerService imageHandlerService;

    @Autowired
    ProductCategoryProductService productCategoryProductService;

    @Autowired
    Environment env;

    @Override
    public Product addProduct(Product product) {

        boolean hasChanged = false;

        Product persistedProduct = productRepository.save(product);

        if (!product.getImage().isEmpty()) {
            persistedProduct.setHasimage(
                    imageHandlerService.saveImage(
                            env.getProperty("front.images.products.items"),
                            persistedProduct.getId(),
                            product
                    )
            );
            hasChanged = true;
        }

        if (product.getProductcategoriesList() != null) {
            addPoductCategoriessToProduct(product);
        }

        return (hasChanged) ? productRepository.save(persistedProduct) : persistedProduct;
    }

    @Override
    public void deleteProductByid(int productId) {
        boolean hasImage = getProductById(productId).isHasimage();
        productRepository.deleteById(productId);

        if ((getProductById(productId)) == null && hasImage) {
            imageHandlerService.deleteImage(env
                    .getProperty("front.images.products.items"), productId);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public List<Product> getRemainigProductsByProductCategoryId(int categoryid) {
        return productRepository.findRemainigProductsByProductCategoryId(categoryid);
    }

    @Override
    public List<Product> getAllProductsByProductCategoryId(int categoryid) {
        return productRepository.findAllProductsByProductCategoryId(categoryid);
    }

    @Override
    public void addPoductCategoriessToProduct(Product product) {
        int productId = product.getId();
        try {
            productCategoryProductService
                    .deleteAllGivenProductCategoryProducts(productCategoryProductService
                            .getAllByProductid(productId));
        } catch (NullPointerException e) {
            LOGGER.error("No Product Categories Found");
        } finally {
            List<ProductCategory> productCategoriesList = product.getProductcategoriesList();
            List<ProductCategoryProduct> productcategoriesProductsList = new ArrayList();
            for (ProductCategory productCategory : productCategoriesList) {
                productcategoriesProductsList.add(new ProductCategoryProduct(productCategory.getId(), productId));
            }
            productCategoryProductService
                    .addAllProductToProductsCategory(productcategoriesProductsList);
        }
    }

}
