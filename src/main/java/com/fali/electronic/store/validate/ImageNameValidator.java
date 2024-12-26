package com.fali.electronic.store.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid, String> {
private Logger logger=LoggerFactory.getLogger(ImageNameValidator.class);
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//logic
		
		logger.info("message from imageNameValidator {}",value);
		if(value.isBlank()) {
			return false;
		}else {
			return true;
		}
		
		
	}

}
