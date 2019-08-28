package com.ateamforce.coffeenow.controller.administrator;

import com.ateamforce.coffeenow.editor.StringToImageEditor;
import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ExtraCategory;
import com.ateamforce.coffeenow.service.ExtraCategoryService;
import com.ateamforce.coffeenow.service.ExtraService;
import com.ateamforce.coffeenow.validator.ExtraValidator;
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
@RequestMapping("/administrator/dashboard/extras")
public class AdminExtrasController {

    @Autowired
    ExtraService extraService;

    @Autowired
    ExtraCategoryService extraCategoryService;

    @Autowired
    ExtraValidator extraValidator;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    LocaleResolver localeResolver;
    
    /**
     * Gets all extras to display them in extras page
     * prepares add new extra form
     * Go to administrator extras page
     * 
     * @param modelmap
     * @param newExtra
     * @return 
     */
    @GetMapping
    public String admin_dashboard_extras(
            ModelMap modelmap, 
            @ModelAttribute("newExtra") Extra newExtra
    ) {
        
        List<Extra> extras = extraService.getAllExtras();

        extras.forEach((extra) -> {
            extra.setExtracategoriesList(extraCategoryService
                    .getAllExtraCategoriesByExtraId(extra.getId()));
        });

        // add extras
        modelmap.addAttribute("extras", extras);
        
        modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());

        // add variable to indicate active sidebar menu
        modelmap.addAttribute("extrasIsActive", "active");

        return "back_admin/dashboard/extras";
    }

    /**
     * INSERT/UPDATE an Extra
     * 
     * @param request
     * @param modelmap
     * @param newExtra
     * @param result
     * @param attributes
     * @return
     * @throws IOException 
     */
    @PostMapping
    public String admin_dashboard_extras_addExtra(
            HttpServletRequest request,
            ModelMap modelmap,
            @ModelAttribute("newExtra") @Valid Extra newExtra,
            BindingResult result, 
            RedirectAttributes attributes
    ) throws IOException {

        if (result.hasErrors()) {

            List<Extra> extras = extraService.getAllExtras();
            for (Extra extra : extras) {
                extra.setExtracategoriesList(extraCategoryService
                        .getAllExtraCategoriesByExtraId(extra.getId()));
            }

            modelmap.addAttribute("mainFormHasErrors", true);
            modelmap.addAttribute("extracategories", extraCategoryService.getAllExtraCategories());
            modelmap.addAttribute("extras", extras);
            modelmap.addAttribute("extrasIsActive", "active");
            return "back_admin/dashboard/extras";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        // save or update
        extraService.addExtra(newExtra);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("admin.menu.extras", null, locale)};
        String mainMessage = "items.updated";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));

        return "redirect:/administrator/dashboard/extras";
    }

    /**
     * DELETE an extra by id
     * 
     * @param request
     * @param extraId
     * @param attributes
     * @return 
     */
    @GetMapping("/delete/{extraId}")
    public String admin_dashboard_extraCategories_deleteExtra(
            HttpServletRequest request,
            @PathVariable int extraId, 
            RedirectAttributes attributes
    ) {
        
        // delete
        extraService.deleteExtraById(extraId);
        
        // add main message
        Locale locale = localeResolver.resolveLocale(request);
        Object[] item = new Object[] {messages.getMessage("extra", null, locale)};
        String mainMessage = "item.deleted";
        attributes.addFlashAttribute("mainMessage", messages.getMessage(mainMessage, item, locale));
        
        return "redirect:/administrator/dashboard/extras";
    }

    /**
     * Allows fields for the new/update extra form returned fields
     * and transforms Lists of strings to lists of extra categories
     * 
     * @param binder 
     */
    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        // adding custom spring validator AND reenabling JSR-303 validations that were
        // disabled because of spring validator
        binder.setValidator(extraValidator);

        // setting allowed fields
        binder.setAllowedFields("id", "title", "image", "extracategoriesList", "language");

        // convert list of strings to list of extra categories
        binder.registerCustomEditor(List.class, "extracategoriesList", new CustomCollectionEditor(List.class) {
            @Override
            protected ExtraCategory convertElement(Object element) {
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
