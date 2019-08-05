package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.event.OnStoreRegistrationCompleteEvent;
import com.ateamforce.coffeenow.exception.UserAlreadyExistException;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.service.AppUserService;
import com.ateamforce.coffeenow.service.impl.UserDetailsServiceImpl;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
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
    UserDetailsServiceImpl userDetailsServiceImpl;
    
    // Store Backend Registration form Page
    @RequestMapping(value = "/store/register", method = RequestMethod.GET)
    public String store_register_form(ModelMap modelmap, @ModelAttribute("newStore") NewStoreDto newStore) {
        return "back_store/register";
    }

    // Store Backend register parse
    @RequestMapping(value = "/store/register", method = RequestMethod.POST)
    public String store_register_parse(
            ModelMap modelmap,
            @ModelAttribute("newStore") @Valid NewStoreDto newStore,
            BindingResult result,
            WebRequest request
    ) {

        AppUser registered = new AppUser();


        if (!result.hasErrors()) {
            registered = createUserAccount(newStore, result);
        }
        if (registered == null) {
            result.rejectValue("email", "email.exists");
        }
        
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnStoreRegistrationCompleteEvent
              (registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            result.rejectValue("email", "email.exists");
        }

        if (result.hasErrors()) {
            return "back_store/register";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        return "redirect:/store/dashboard";
    }
    
    @RequestMapping(value = "/store/registerconfirm", method = RequestMethod.GET)
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        Locale locale = request.getLocale();
        final String result = appUserService.validateAppUserToken(token);
        if (result.equals("valid")) {
            final AppUser user = appUserService.getUserByToken(token);
            authWithoutPassword(user);
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            return "redirect:/store/dashboard?language=" + locale.getLanguage();
        }

        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/store?baduser=true&language=" + locale.getLanguage();
    }

    private AppUser createUserAccount(NewStoreDto newStore, BindingResult result) {
        AppUser registered = null;
        try {
            registered = appUserService.registerNewStore(newStore);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }
    
    public void authWithoutPassword(AppUser user) {

        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetailsServiceImpl.loadUserByUsername(user.getEmail()).getAuthorities();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
