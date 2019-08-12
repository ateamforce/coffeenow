package com.ateamforce.coffeenow.controller;

import com.ateamforce.coffeenow.dto.NewStoreDto;
import com.ateamforce.coffeenow.dto.PasswordDto;
import com.ateamforce.coffeenow.event.OnStoreRegistrationCompleteEvent;
import com.ateamforce.coffeenow.exception.UserAlreadyExistException;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.model.TokenPasswordReset;
import com.ateamforce.coffeenow.model.TokenVerification;
import com.ateamforce.coffeenow.service.AppUserService;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private AppUserService appUserService;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private LocaleResolver localeResolver;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private Environment env;
    
    /**
     * Store Backend Registration form Page
     * 
     * @param modelmap
     * @param newStore
     * @return 
     */
    @RequestMapping(value = "/store/register", method = RequestMethod.GET)
    public String store_register_form(ModelMap modelmap, @ModelAttribute("newStore") NewStoreDto newStore) {
        return "back_store/register";
    }

    /**
     * Store Backend register parse
     * 
     * @param request
     * @param modelmap
     * @param newStore
     * @param result
     * @param attributes
     * @return 
     */
    @RequestMapping(value = "/store/register", method = RequestMethod.POST)
    public String store_register_parse(
            HttpServletRequest request,
            ModelMap modelmap,
            @ModelAttribute("newStore") @Valid NewStoreDto newStore,
            BindingResult result,
            RedirectAttributes attributes
    ) {

        if (!result.hasErrors()) {
            AppUser registered = new AppUser();
            registered = createUserAccount(newStore);
            
            if (registered == null) {
                result.rejectValue("email", "email.exists");
            }
            else {
                try {
                    eventPublisher.publishEvent(new OnStoreRegistrationCompleteEvent
                      (registered, localeResolver.resolveLocale(request), getAppUrl(request)));
                } catch (Exception me) {
                    result.rejectValue("email", "email.exists");
                }
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
    
    /**
     * Store backend register confirmation parsing
     * 
     * @param request
     * @param model
     * @param token
     * @param attributes
     * @return
     * @throws UnsupportedEncodingException 
     */
    @GetMapping("/store/register/confirm")
    public String confirmRegistration(
            final HttpServletRequest request, 
            final Model model, 
            @RequestParam("token") final String token, 
            RedirectAttributes attributes
    ) {
        
        final AppUser user = appUserService.getAppUserByVerificationToken(token);
        final String result = appUserService.validateVerificationToken(token); // this also deletes the persisted token if validation is successful 
                                                                          // or it's expiration date has passed
        if (result.equals("valid")) {
            authWithoutPassword(user, request);
            attributes.addFlashAttribute("mainMessage", messages.getMessage("message.accountVerified", null, localeResolver.resolveLocale(request)));
            return "redirect:/store/dashboard";
        }

        attributes.addFlashAttribute("mainMessage", messages.getMessage("auth.message." + result, null, localeResolver.resolveLocale(request)));
        return "redirect:/store";
    }
    
    /**
     * Store Backend Resend Registration confirmation/validation email form
     * 
     * @param modelmap
     * @return 
     */
    @RequestMapping(value = "/store/register/resend/confirm", method = RequestMethod.GET)
    public String store_register_resend_confirmation_form(ModelMap modelmap) {
        return "back_store/registerResendConfirm";
    }

    /**
     * Store Backend Resend Registration confirmation/validation email parse
     * 
     * @param request
     * @param attributes
     * @param modelmap
     * @param email
     * @return 
     */
    @RequestMapping(value = "/store/register/resend/confirm", method = RequestMethod.POST)
    public String store_register_resend_confirmation_parse(
            HttpServletRequest request,
            RedirectAttributes attributes,
            ModelMap modelmap,
            @RequestParam("email") String email
    ) {

        final AppUser user = appUserService.getUserByEmail(email);
        
        // check is user exists and is NOT enabled
        if ( user != null ) {
            if ( !user.isEnabled() ) {
                TokenVerification oldToken = appUserService.getVerificationTokenByAppUser(user);
                final TokenVerification newToken;
                // check if old token sxists
                if ( oldToken != null ) {
                    newToken = appUserService.generateNewVerificationToken(oldToken);
                }
                else {
                    final String token = UUID.randomUUID().toString();
                    newToken = appUserService.createVerificationTokenForAppUser(user, token);
                }
                mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), localeResolver.resolveLocale(request), newToken, user, request));
                attributes.addFlashAttribute("mainMessage", messages.getMessage("message.newTokenCreated", null, localeResolver.resolveLocale(request)));
            }
            else {
                attributes.addFlashAttribute("mainMessage", messages.getMessage("message.userIsEnabled", null, localeResolver.resolveLocale(request)));
            }
        }
        else {
            attributes.addFlashAttribute("mainMessage", messages.getMessage("message.userNotFound", null, localeResolver.resolveLocale(request)));
        }
        
        return "redirect:/store";
    }
    
    /**
     * Store Backend Reset password email FORM
     * 
     * @param modelmap
     * @return 
     */
    @RequestMapping(value = "/store/reset/password", method = RequestMethod.GET)
    public String store_reset_password_form(ModelMap modelmap) {
        return "back_store/resetPassword";
    }
    
    /**
     * Store Backend Reset password email PARSE
     * 
     * @param request
     * @param attributes
     * @param modelmap
     * @param email
     * @return 
     */
    @RequestMapping(value = "/store/reset/password", method = RequestMethod.POST)
    public String store_reset_password_parse(
            HttpServletRequest request,
            RedirectAttributes attributes,
            ModelMap modelmap,
            @RequestParam("email") String email
    ) {

        final AppUser user = appUserService.getUserByEmail(email);
        
        // check is user exists and IS enabled
        if ( user != null ) {
            if ( user.isEnabled() ) {
                final String token = UUID.randomUUID().toString();
                TokenPasswordReset newToken = appUserService.createPasswordResetTokenForAppUser(user, token);
                mailSender.send(constructResetPasswordTokenEmail(getAppUrl(request), localeResolver.resolveLocale(request), newToken, user, request));
                attributes.addFlashAttribute("mainMessage", messages.getMessage("message.email.instructions", null, localeResolver.resolveLocale(request)));
            }
            else {
                attributes.addFlashAttribute("mainMessage", messages.getMessage("message.userIsDisabled", null, localeResolver.resolveLocale(request)));
            }
        }
        else {
            attributes.addFlashAttribute("mainMessage", messages.getMessage("message.userNotFound", null, localeResolver.resolveLocale(request)));
        }
        
        return "redirect:/store";
    }
    
    /**
     * Store Backend Change password authorization parsing and FORM
     * 
     * @param request
     * @param model
     * @param modelmap
     * @param passwordDto
     * @param id
     * @param token
     * @param attributes
     * @return 
     */
    @RequestMapping(value = "/store/reset/password/change", method = RequestMethod.GET)
    public String store_change_password_form(
            HttpServletRequest request,
            final Model model, 
            ModelMap modelmap,
            @ModelAttribute("passwordDto") PasswordDto passwordDto,
            @RequestParam("id") long id,
            @RequestParam("token") String token,
            RedirectAttributes attributes
    ) {
        
        String auth = appUserService.validatePasswordResetToken(id, token);
        
        if (auth.equals("valid")) {
            modelmap.addAttribute("id", id);
            modelmap.addAttribute("token", token);
            modelmap.addAttribute("mainMessage", messages.getMessage("message.changePassword", null, localeResolver.resolveLocale(request)));
            return "back_store/changePassword";
        }
        
        attributes.addFlashAttribute("mainMessage", messages.getMessage("auth.message." + auth, null, localeResolver.resolveLocale(request)));
        return "redirect:/store";
    }
    
    /**
     * Store Backend Change password PARSE
     * 
     * @param request
     * @param passwordDto
     * @param result
     * @param id
     * @param token
     * @param attributes
     * @return 
     */
    @RequestMapping(value = "/store/reset/password/change", method = RequestMethod.POST)
    public String store_change_password_parse(
            HttpServletRequest request,
            ModelMap modelmap,
            @ModelAttribute("passwordDto") @Valid PasswordDto passwordDto,
            BindingResult result,
            @RequestParam("id") long id,
            @RequestParam("token") String token,
            RedirectAttributes attributes
    ) {
        
        if (result.hasErrors()) {
            modelmap.addAttribute("id", id);
            modelmap.addAttribute("token", token);
            return "back_store/changePassword";
        }
        
        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }
        
        String auth = appUserService.validatePasswordResetToken(id, token);
        if (auth.equals("valid")) {
            final AppUser user = appUserService.getAppUserByPasswordResetToken(token);
            appUserService.changeAppUserPassword(user, passwordDto.getPassword());
            appUserService.deletePasswordResetToken(token);
            attributes.addFlashAttribute("mainMessage", messages.getMessage("success.passwordChange", null, localeResolver.resolveLocale(request)));
        }

        return "redirect:/store";
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////

    // creates a new store account
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
    
    // Resend Verification Token Email
    private SimpleMailMessage constructResendVerificationTokenEmail(
            final String contextPath, 
            final Locale locale, 
            final TokenVerification newToken, 
            final AppUser user, 
            HttpServletRequest request
    ) {
        final String confirmationUrl = contextPath + "/store/register/confirm?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail(messages.getMessage("message.resendConf", null, localeResolver.resolveLocale(request)), message + " \r\n" + confirmationUrl, user);
    }
    
    // Reset Password Email
    private SimpleMailMessage constructResetPasswordTokenEmail(
            final String contextPath, 
            final Locale locale, 
            final TokenPasswordReset newToken, 
            final AppUser user, 
            HttpServletRequest request
    ) {
        final String url = contextPath + "/store/reset/password/change?id=" + user.getId() + "&token=" + newToken.getToken();
        final String message = messages.getMessage("message.resetPasswordExp", null, locale);
        return constructEmail(messages.getMessage("message.resetPassword", null, localeResolver.resolveLocale(request)), message + " \r\n" + url, user);
    }
    
    // utility method for sending emails
    private SimpleMailMessage constructEmail(String subject, String body, AppUser user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
    
    // returns the current app's url
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
