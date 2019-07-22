package com.ateamforce.coffeenow.validator;

import com.ateamforce.coffeenow.model._ImageCarrier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// custom spring validator that checks for image size
@Component
public class ImageValidator implements Validator {

	private final long allowedSize = 1000000;

	// indicates whether the validator can validate a specific class
        @Override
	public boolean supports(Class<?> clazz) {
		return _ImageCarrier.class.isAssignableFrom(clazz);
	}

        @Override
	public void validate(Object target, Errors errors) {
		_ImageCarrier imageCarrier = (_ImageCarrier) target;
		if (imageCarrier.getImage() != null && imageCarrier.getImage().getSize() > allowedSize) { // 1MB
			errors.rejectValue("image", "com.ateamforce.coffeenow.validator.ImageValidator.message");
		}
	}
}