package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.event.OnStoreRegistrationCompleteEvent;
import com.ateamforce.coffeenow.exception.UserAlreadyExistException;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.service.AppUserService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * A controller for registration related methods
 * 
 * @author Sakel
 */
@Controller
public class AccountController {
    
    @Autowired
    AppUserService appUserService;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    LocaleResolver localeResolver;
    
    // Store Backend Registration form Page
    @RequestMapping(value = "/store/register", method = RequestMethod.GET)
    public String store_register_form(ModelMap modelmap, @ModelAttribute("newStore") NewStoreDto newStore) {
        return "back_store/register";
    }

    // Store Backend register parse
    @RequestMapping(value = "/store/register", method = RequestMethod.POST)
    public String store_register_parse(
            HttpServletRequest request,
            ModelMap modelmap,
            @ModelAttribute("newStore") @Valid NewStoreDto newStore,
            RedirectAttributes attributes,
            BindingResult result
    ) {

        AppUser registered = new AppUser();


        if (!result.hasErrors()) {
            registered = createUserAccount(newStore);
        }
        if (registered == null) {
            result.rejectValue("email", "email.exists");
        }
        else {
            try {
                String appUrl = request.getContextPath();
                // we publish the event listener, that will send confirmation/activation email, passing request.getLocale() 
                // which is the browser (client) locale, not the app locale. So the email's lalnguage depends on the browser's
                // language, not the app's.
                eventPublisher.publishEvent(new OnStoreRegistrationCompleteEvent
                  (registered, request.getLocale(), appUrl));
            } catch (Exception me) {
                result.rejectValue("email", "email.exists");
            }
        }

        if (result.hasErrors()) {
            return "back_store/register";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        attributes.addFlashAttribute("mainMessage", messages.getMessage("message.needsToBeVerified", null, localeResolver.resolveLocale(request)));
        return "redirect:/store";
    }
    
    @GetMapping("/store/register/confirm")
    public String confirmRegistration(
            final HttpServletRequest request, 
            final Model model, 
            @RequestParam("token") final String token, 
            RedirectAttributes attributes
    ) throws UnsupportedEncodingException {
        
        final AppUser user = appUserService.getUserByToken(token);
        final String result = appUserService.validateAppUserToken(token); // this also deletes the persisted token if validation is successful 
                                                                          // or it's expiration date has passed
        if (result.equals("valid")) {
            authWithoutPassword(user, request);
            attributes.addFlashAttribute("mainMessage", messages.getMessage("message.accountVerified", null, localeResolver.resolveLocale(request)));
            return "redirect:/store/dashboard";
        }

        attributes.addFlashAttribute("mainMessage", messages.getMessage("auth.message." + result, null, localeResolver.resolveLocale(request)));
        return "redirect:/store";
    }

    private AppUser createUserAccount(NewStoreDto newStore) {
        AppUser registered = null;
        try {
            registered = appUserService.registerNewStore(newStore);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }
    
    // enable user auto login after validating email
    // https://stackoverflow.com/questions/36937414/auto-login-spring-security
    public void authWithoutPassword(AppUser user, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);      
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

}
