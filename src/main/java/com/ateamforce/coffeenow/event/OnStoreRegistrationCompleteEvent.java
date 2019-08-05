package com.ateamforce.coffeenow.event;

import com.ateamforce.coffeenow.model.AppUser;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author Sakel
 */
@SuppressWarnings("serial")
public class OnStoreRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final AppUser user;

    public OnStoreRegistrationCompleteEvent(final AppUser user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public AppUser getUser() {
        return user;
    }

}