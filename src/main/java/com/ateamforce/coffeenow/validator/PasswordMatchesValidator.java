package com.ateamforce.coffeenow.validator;

import com.ateamforce.coffeenow.annotation.PasswordMatches;
import com.ateamforce.coffeenow.dto.NewStoreDto;
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
        final NewStoreDto newStoreDto = (NewStoreDto) obj;
        return newStoreDto.getPassword().equals(newStoreDto.getPasswordRepeat());    
    }     
}

