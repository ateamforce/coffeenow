package com.ateamforce.coffeenow.controller.administrator.rest;

import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a REST controller responsible for everything that has to do with
 * the relationship between products and product categories, which is depicted in 
 * the many to many join table productcategories_products of our database.
 * 
 * We need rest in order to send and receive json because we will be manipulating their 
 * relationship through javascript/jquery
 * 
 * @author Sakel
 */
@RestController
@RequestMapping("/administrator/dashboard/productcategories_products")
public class AdminProductCategoriesProductsRestController {

    @Autowired
    ProductCategoryService productCategoryService;
    
    // return a json object containing two named lists of products of this productCategory
    @GetMapping("/products/{productCategoryId}")
    public String admin_dashboard_productCategory_getproducts(@PathVariable int productCategoryId) {
        
        // Here we need to return 2 lists as JSON, 
        // 1. all of the products already belonging to productCategoryId (from sql table products)
        //    e.g. SELECT * FROM products WHERE id IN (SELECT p.id FROM (products AS p INNER JOIN productcategories_products AS pcp ON p.id = pcp.productId) WHERE pcp.categoryid = :productCategoryId)
        // 2. all of the products NOT belonging to productCategoryId (from sql table products)
        //    e.g. SELECT * FROM products WHERE id NOT IN (SELECT p.id FROM (products AS p INNER JOIN productcategories_products AS pcp ON p.id = pcp.productId) WHERE pcp.categoryid = :productCategoryId)

        // The annotation @RestController instructs Spring to convert all Java objects that are returned from the request-mapping methods into JSON/XML format and send a response in the body of the HTTP response
        return "Instead of this string, return the object you want to send, and it will be sent as json";
    }
    
    // manipulate rows in table productcategories_products
    // here we need the ajax request to be a POST in order to send the json containing a list of products 
    // The @RequestBody will pick up any post parameter with the name productsToAdd, and transform it to the provided type
    @PostMapping("/products/update/{productCategoryId}")
    public void admin_dashboard_productCategory_removeaddproducts(@PathVariable int productCategoryId, @RequestBody List<Product> productsToAdd) {
        // We will use brute force for all updates of join tables. Which means that basically we don't update, we only insert and delete.
        // What we do is :
        // First, we delete ALL rows with the specified productCategoryId
        // Then, we insert ALL the new ones (productsToAdd), if any
        // Obviously the "new ones", could contain products that were already in that category, but 
        // since we deleted ALL of them, we treat them as new and insert them
    }
    
    // return a json object containing two named lists of productCategories of this product
    @GetMapping("/productcategories/{productId}")
    public String admin_dashboard_product_getproductCategories(@PathVariable int productId) {
        return "Instead of this string, return the object you want to send, and it will be sent as json";
    }
    
    // update (as above) the productCategories of this product
    @PostMapping("/productcategories/update/{productId}")
    public void admin_dashboard_product_removeaddproductCategories(@PathVariable int productId, @RequestBody List<ProductCategory> categoriesToAdd) {

    }
    
}
