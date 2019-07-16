package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.dto.ProductCategoryDto;
import com.ateamforce.coffeenow.dto.ProductDto;
import com.ateamforce.coffeenow.dto.service.ProductCategoryDtoService;
import com.ateamforce.coffeenow.dto.service.ProductDtoService;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a REST controller responsible for everything that has to do with the
 * relationship between products and product categories, which is depicted in
 * the many to many join table productcategories_products of our database.
 *
 * We need rest in order to send and receive json because we will be
 * manipulating their relationship through javascript/jquery
 *
 * @author Sakel
 */
@RestController
@RequestMapping("/administrator/dashboard/productcategories_products")
public class AdminProductCategoriesProductsRestController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductCategoryDtoService productCategoryDtoService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductDtoService productDtoService;

    // return a json object containing two named lists of products of this productCategory
    // return a json object containing two named lists of products of this productCategory
    @GetMapping("/products/{productCategoryId}")
    public Map<String, List<ProductDto>> admin_dashboard_productCategory_getproducts(@PathVariable int productCategoryId) {
        List<ProductDto> belongingProducts = new ArrayList(productDtoService
                .getAllProductsByProductCategoryId(productCategoryId));
        List<ProductDto> notBelongingProducts = new ArrayList(productDtoService
                .getRemainigProductsByProductCategoryId(productCategoryId));
        Map<String, List<ProductDto>> products = new HashMap();
        products.put("belongingProducts", belongingProducts);
        products.put("notBelongingProducts", notBelongingProducts);
        return products;
    }

    // manipulate rows in table productcategories_products
    // here we need the ajax request to be a POST in order to send the json containing a list of products 
    // The @RequestBody will pick up any post parameter with the name productsToAdd, and transform it to the provided type
    @PostMapping("/products/update/{productCategoryId}")
    public void admin_dashboard_productCategory_removeaddproducts(@PathVariable int productCategoryId, @RequestBody List<Product> productsToAdd) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(productCategoryId);
        productCategory.getProductsList().clear();
        productCategory.getProductsList().addAll(productsToAdd);
    }

    // return a json object containing two named lists of productCategories of this product
    @GetMapping("/productcategories/{productId}")
    public Map<String, List<ProductCategoryDto>> admin_dashboard_product_getproductCategories(@PathVariable int productId) {
        List<ProductCategoryDto> belongingProductCategories = new ArrayList(productCategoryDtoService
                .getAllProductCategoriesByProductId(productId));
        List<ProductCategoryDto> notBelongingProductCategories = new ArrayList(productCategoryDtoService
                .getRemainigProductCategoriesByProductId(productId));
        Map<String, List<ProductCategoryDto>> productcategories = new HashMap();
        productcategories.put("belongingProductCategories", belongingProductCategories);
        productcategories.put("notBelongingProductCategories", notBelongingProductCategories);
        return productcategories;
    }
    
    // update (as above) the productCategories of this product
    @PostMapping("/productcategories/update/{productId}")
    public void admin_dashboard_product_removeaddproductCategories(@PathVariable int productId, @RequestBody List<ProductCategory> categoriesToAdd) {
        Product product = productService.getProductById(productId);
        product.getProductCategoriesList().clear();
        product.getProductCategoriesList().addAll(categoriesToAdd);
    }
}
