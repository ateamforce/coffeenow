package com.ateamforce.coffeenow.model;

/**
 * 
 * An interface mean to be implemented by business objects that have a password associated with them
 *
 * @author Sakel
 */
public interface _PasswordCarrier {
    String getPassword();
    String getPasswordRepeat();
}
