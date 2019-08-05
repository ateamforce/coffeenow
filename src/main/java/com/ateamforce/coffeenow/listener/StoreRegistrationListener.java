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

    // API

    @Override
    public void onApplicationEvent(final OnStoreRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnStoreRegistrationCompleteEvent event) {
        final AppUser user = event.getUser();
        final String token = UUID.randomUUID().toString();
        

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnStoreRegistrationCompleteEvent event, final AppUser user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = messages.getMessage("message.regConf", null, event.getLocale()) + " - coffeenow.gr";
        final String confirmationUrl = event.getAppUrl() + "/registerconfirm?token=" + token;
        final String message = messages.getMessage("message.regSucc", null, event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom("coffeenow_gr@hotmail.com");
        return email;
    }

}