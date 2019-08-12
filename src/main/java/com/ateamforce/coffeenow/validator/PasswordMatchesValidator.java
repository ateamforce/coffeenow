package com.ateamforce.coffeenow.validator;

import com.ateamforce.coffeenow.annotation.PasswordMatches;
import com.ateamforce.coffeenow.model._PasswordCarrier;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Sakel
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> { 
     
    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {       
    }
    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context){   
        final _PasswordCarrier passwordCarrier = (_PasswordCarrier) obj;
        return passwordCarrier.getPassword().equals(passwordCarrier.getPasswordRepeat());    
    }     
}

