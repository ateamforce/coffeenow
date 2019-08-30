package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import com.ateamforce.coffeenow.validator.ProductCategoryValidator;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author alexa
 */
@Controller
@RequestMapping("/administrator/dashboard/productcategories")
public class AdminProductCategoriesController {
    
    private final static Logger LOGGER = Logger.getLogger(AdminProductCategoriesController.class);

    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    @Autowired
    ProductCategoryValidator productCategoryValidator;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    LocaleResolver localeResolver;
    
    /**
     * Gets all productcaregories to display them in productcategories page
     * prepares add new product category form
     * Go to administrator productcategories page
     * 
     * @param modelmap
     * @param productCategory
     * @return 
     */
    @GetMapping
    public String admin_dashboard_productcategories(
            ModelMap modelmap, 
            @ModelAttribute("productCategory") ProductCategory productCategory
    ) {

        // add product categories with ExtraCategories and Products
        List<ProductCategory> productcategories = productCategoryService.getAllProductCategories();
        for (ProductCategory productcategory : productcategories) {
            productcategory.setProductsList(productService.getAllProductsByProductCategoryId(productcategory.getId()));
            productcategory.setExtrascategoriesList(extraCategoryService.getAllExtraCategoriesByProductCategoryId(productcategory.getId()));
        }

        modelmap.addAttribute("productcategories", productcategories);

        // add products
        modelmap.addAttribute("products", productService.getAllProducts());

        // add extras categories
        modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("productcategoriesIsActive", "active");

        return "back_admin/dashboard/product_categories";
    }

    /**
     * INSERT/UPDATE a product category
     * 
     * @param request
     * @param modelmap
     * @param productCategory
     * @param result
     * @param attributes
     * @return
     * @throws IOException 
     */
    @PostMapping
    public String admin_dashboard_productCategories_addProductCategory(
            HttpServletRequest request,
            ModelMap modelmap, 
            @ModelAttribute("productCategory") @Valid ProductCategory productCategory, 
            BindingResult result,
            RedirectAttributes attributes
    ) throws IOException {
        
        if (result.hasErrors()) {
            
            List<ProductCategory> productcategories = productCategoryService.getAllProductCategories();
            for (ProductCategory productcategory : productcategories) {
                productcategory.setProductsList(productService.getAllProductsByProductCategoryId(productcategory.getId()));
                productcategory.setExtrascategoriesList(extraCategoryService.getAllExtraCategoriesByProductCategoryId(productcategory.getId()));
            }
            
            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("productcategories", productcategories);
            modelmap.addAttribute("products", productService.getAllProducts());
            modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());
            modelmap.addAttribute("productcategoriesIsActive", "active");
            return "back_admin/dashboard/product_categories";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        // save or update
        productCategoryService.addProductCategory(productCategory);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("admin.menu.productcategories", null, locale)};
        String mainMessage = "items.updated";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));

        return "redirect:/administrator/dashboard/productcategories";
    }

    /**
     * DELETE a product category by id
     * 
     * @param request
     * @param productCategoryId
     * @param attributes
     * @return 
     */
    @GetMapping("/delete/{productCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(
            HttpServletRequest request,
            @PathVariable int productCategoryId,
            RedirectAttributes attributes
    ) {
        
        // delete
        productCategoryService.deleteProductCategoryById(productCategoryId);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("productcategory", null, locale)};
        String mainMessage = "item.deleted";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/productcategories";
    }
    
    /**
     * Allows fields for the new/update ProductCategory form returned fields
     * and transforms Lists of strings to lists of products and/or extra categories
     * 
     * @param binder 
     */
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(productCategoryValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "parent", "image", "extrascategoriesList", "productsList", "language");

        // convert list of strings to list of products
        binder.registerCustomEditor(List.class,"productsList", new CustomCollectionEditor(List.class){
            @Override
            protected Product convertElement(Object element){
                if (element instanceof String) {
                    return productService.getProductById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });

        // convert list of strings to list of extra categories
        binder.registerCustomEditor(List.class,"extrascategoriesList", new CustomCollectionEditor(List.class){
            @Override
            protected ExtraCategory convertElement(Object element){
                if (element instanceof String) {
                    return extraCategoryService.getExtraCategoryById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
        // convert base64 encoded image (string) to MultiPart file
        binder.registerCustomEditor(MultipartFile.class, "image", new StringToImageEditor());
            
    }

}
