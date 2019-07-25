/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service;

import com.ateamforce.coffeenow.dto.ProductDto;
import java.util.List;

/**
 *
 * @author alexa
 */
public interface ProductDtoService {

    ProductDto addProduct(ProductDto product);

    void deleteProductByid(int productId);

    void updateProduct(ProductDto updatedProduct);

    ProductDto getProductById(int productId);

    List<ProductDto> getAllProducts();

    List<ProductDto> getRemainigProductsByProductCategoryId(int categoryid);

    List<ProductDto> getAllProductsByProductCategoryId(int categoryid);
}
