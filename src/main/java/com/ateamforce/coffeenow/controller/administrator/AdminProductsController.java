package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.Product;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.service.ProductService;
import com.ateamforce.coffeenow.validator.ProductValidator;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
@RequestMapping("/administrator/dashboard/products")
public class AdminProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductValidator productValidator;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    LocaleResolver localeResolver;

    /**
     * INSERT/UPDATE a product
     * 
     * @param request
     * @param modelmap
     * @param newProduct
     * @param result
     * @param attributes
     * @return
     * @throws IOException 
     */
    @PostMapping
    public String admin_dashboard_products_addProduct(
            HttpServletRequest request,
            ModelMap modelmap,
            @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult result, 
            RedirectAttributes attributes
    ) throws IOException {

        if (result.hasErrors()) {

            List<Product> products = productService.getAllProducts();
            for (Product product : products) {
                product.setProductcategoriesList(productCategoryService.getAllProductCategoriesByProductId(product.getId()));
            }

            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());
            modelmap.addAttribute("products", products);
            modelmap.addAttribute("productsIsActive", "active");
            return "back_admin/dashboard/products";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        // save or update
        productService.addProduct(newProduct);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("admin.menu.products", null, locale)};
        String mainMessage = "items.updated";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));

        return "redirect:/administrator/dashboard/products";
    }

    /**
     * DELETE a product by id
     * 
     * @param request
     * @param productId
     * @param attributes
     * @return 
     */
    @GetMapping("/delete/{productId}")
    public String admin_dashboard_productCategories_deleteProduct(
            HttpServletRequest request,
            @PathVariable int productId, 
            RedirectAttributes attributes
    ) {
        
        // delete
        productService.deleteProductByid(productId);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("product", null, locale)};
        String mainMessage = "item.deleted";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/products";
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
        binder.setValidator(productValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "description", "image", "productcategoriesList", "language");

        // convert list of strings to list of product categories
        binder.registerCustomEditor(List.class, "productcategoriesList", new CustomCollectionEditor(List.class) {
            @Override
            protected ProductCategory convertElement(Object element) {
                if (element instanceof String) {
                    return productCategoryService.getProductCategoryById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
        // convert base64 encoded image (string) to MultiPart file
        binder.registerCustomEditor(MultipartFile.class, "image", new StringToImageEditor());

    }

}
