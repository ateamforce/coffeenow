package com.ateamforce.coffeenow.validator;

import com.ateamforce.coffeenow.annotations.PasswordMatches;
import com.ateamforce.coffeenow.model.AppUser;
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
        final AppUser user = (AppUser) obj;
        return user.getPassword().equals(user.getPasswordRepeat());    
    }     
}

