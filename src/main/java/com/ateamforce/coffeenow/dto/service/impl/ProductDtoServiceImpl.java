/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto.service.impl;

import com.ateamforce.coffeenow.dto.ProductDto;
import com.ateamforce.coffeenow.dto.repository.ProductDtoRepository;
import com.ateamforce.coffeenow.dto.service.ProductDtoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexa
 */
@Service
public class ProductDtoServiceImpl implements ProductDtoService {

    @Autowired
    ProductDtoRepository productDtoRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return productDtoRepository.save(productDto);
    }

    @Override
    public void deleteProductByid(int productId) {
        productDtoRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productDtoRepository.findAllProducts();
    }

    @Override
    public ProductDto getProductById(int productId) {
        return productDtoRepository.findProductById(productId);
    }

    @Override
    public void updateProduct(ProductDto updatedProduct) {
        productDtoRepository.save(updatedProduct);
    }

    @Override
    public List<ProductDto> getRemainigProductsByProductCategoryId(int categoryid) {
        return productDtoRepository.findRemainigProductsByProductCategoryId(categoryid);
    }

    @Override
    public List<ProductDto> getAllProductsByProductCategoryId(int categoryid) {
        return productDtoRepository.findAllProductsByProductCategoryId(categoryid);
    }

}
