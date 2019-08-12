package com.ateamforce.coffeenow.util;

/**
 * 
 * An interface meant to be implemented by business objects that have a password associated with them.
 * Works in combination with {@link com.ateamforce.coffeenow.validator.PasswordMatchesValidator @PasswordMatches} annotation
 *
 * @author Sakel
 */
public interface PasswordService {
    String getPassword();
    String getPasswordRepeat();
}
