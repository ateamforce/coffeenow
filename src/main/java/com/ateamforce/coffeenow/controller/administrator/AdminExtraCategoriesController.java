package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.model.ProductCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.service.ProductCategoryService;
import com.ateamforce.coffeenow.validator.ExtraCategoryValidator;
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
@RequestMapping("/administrator/dashboard/extracategories")
public class AdminExtraCategoriesController {
    
    @Autowired
    ExtraCategoryService extraCategoryService;
    
    @Autowired
    ExtraService extraService;
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    ExtraCategoryValidator extraCategoryValidator;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    LocaleResolver localeResolver;
    
    /**
     * Gets all extracaregories to display them in extracaregories page
     * prepares add new extra category form
     * Go to administrator extracaregories page
     * 
     * @param modelmap
     * @param extraCategory
     * @return 
     */
    @GetMapping
    public String admin_dashboard_extrascategories(
            ModelMap modelmap, 
            @ModelAttribute("extraCategory") ExtraCategory extraCategory
    ) {

        List<ExtraCategory> extracategories = extraCategoryService.getAllExtraCategories();
        for (ExtraCategory extracategory : extracategories) {
            extracategory.setExtrasList(extraService.getAllExtrasByExtraCategoryId(extracategory.getId()));
            extracategory.setProductcategoriesList(productCategoryService.getAllProductCategoriesByExtraCategoryId(extracategory.getId()));
        }

        // add extras categories
        modelmap.addAttribute("extracategories", extracategories);

        //add all extras
        modelmap.addAttribute("extras", extraService.getAllExtras());

        //add all product categories
        modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extracategoriesIsActive", "active");

        return "back_admin/dashboard/extra_categories";
    }

    /**
     * INSERT/UPDATE a extra category
     * 
     * @param request
     * @param modelmap
     * @param extraCategory
     * @param result
     * @param attributes
     * @return
     * @throws IOException 
     */
    @PostMapping
    public String admin_dashboard_extraCategories_addExtraCategory(
            HttpServletRequest request,
            ModelMap modelmap,
            @ModelAttribute("extraCategory") @Valid ExtraCategory extraCategory,
            BindingResult result, 
            RedirectAttributes attributes
    ) throws IOException {
        
        if (result.hasErrors()) {
            
            List<ExtraCategory> extracategories = extraCategoryService.getAllExtraCategories();
            for (ExtraCategory extracategory : extracategories) {
                extracategory.setExtrasList(extraService.getAllExtrasByExtraCategoryId(extracategory.getId()));
                extracategory.setProductcategoriesList(productCategoryService.getAllProductCategoriesByExtraCategoryId(extracategory.getId()));
            }
            
            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("extracategories", extracategories);
            modelmap.addAttribute("extras", extraService.getAllExtras());
            modelmap.addAttribute("productcategories", productCategoryService.getAllProductCategories());
            modelmap.addAttribute("extracategoriesIsActive", "active");
            return "back_admin/dashboard/extra_categories";
        }
        
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        
        // save or update
        extraCategoryService.addExtraCategory(extraCategory);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("admin.menu.extrascategories", null, locale)};
        String mainMessage = "items.updated";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/extracategories";
    }

    /**
     * DELETE an extra category by id
     * 
     * @param request
     * @param extraCategoryId
     * @param attributes
     * @return 
     */
    @GetMapping("/delete/{extraCategoryId}")
    public String admin_dashboard_productCategories_deleteProductCategory(
            HttpServletRequest request,
            @PathVariable int extraCategoryId, 
            RedirectAttributes attributes
    ) {
        
        // delete
        extraCategoryService.deleteExtraCategoryById(extraCategoryId);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("extracategory", null, locale)};
        String mainMessage = "item.deleted";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/extracategories";
    }

    /**
     * Allows fields for the new/update ExtraCategory form returned fields
     * and transforms Lists of strings to lists of extra and/or product categories
     * 
     * @param binder 
     */
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(extraCategoryValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "parent", "image", "productcategoriesList", "extrasList", "language");
        
        // convert list of strings to list of extras
        binder.registerCustomEditor(List.class, "extrasList", new CustomCollectionEditor(List.class) {
            @Override
            protected Extra convertElement(Object element) {
                if (element instanceof String) {
                    return extraService.getExtraById(Integer.parseInt(element.toString()));
                }
                return null;
            }
        });
        
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
