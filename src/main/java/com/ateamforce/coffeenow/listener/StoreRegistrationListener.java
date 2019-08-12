package com.ateamforce.coffeenow.listener;

import com.ateamforce.coffeenow.event.OnStoreRegistrationCompleteEvent;
import com.ateamforce.coffeenow.model.AppUser;
import com.ateamforce.coffeenow.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.UUID;
import org.springframework.core.env.Environment;

/**
 *
 * @author Sakel
 */
@Component
public class StoreRegistrationListener implements ApplicationListener<OnStoreRegistrationCompleteEvent> {
    @Autowired
    private AppUserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnStoreRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnStoreRegistrationCompleteEvent event) {
        final AppUser user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForAppUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    // Setup registration confirmation email
    private SimpleMailMessage constructEmailMessage(final OnStoreRegistrationCompleteEvent event, final AppUser user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = messages.getMessage("message.regConf", null, event.getLocale()) + " - coffeenow.gr";
        final String confirmationUrl = event.getAppUrl() + "/store/register/confirm?token=" + token;
        final String message = messages.getMessage("message.regSucc", null, event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}