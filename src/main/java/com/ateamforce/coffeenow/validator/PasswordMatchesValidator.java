package com.ateamforce.coffeenow.validator;

import com.ateamforce.coffeenow.annotation.PasswordMatches;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.ateamforce.coffeenow.util.PasswordService;

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
        final PasswordService passwordCarrier = (PasswordService) obj;
        return passwordCarrier.getPassword().equals(passwordCarrier.getPasswordRepeat());    
    }     
}

