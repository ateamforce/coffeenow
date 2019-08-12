package com.ateamforce.coffeenow.util;

/**
 * 
 * An interface mean to be implemented by business objects that have a password associated with them
 *
 * @author Sakel
 */
public interface PasswordService {
    String getPassword();
    String getPasswordRepeat();
}
