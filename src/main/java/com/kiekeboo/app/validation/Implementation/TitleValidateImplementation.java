package com.kiekeboo.app.validation.Implementation;

import com.kiekeboo.app.validation.TitleValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleValidateImplementation implements ConstraintValidator<TitleValidate, String> {

    protected final Logger logger = LoggerFactory.getLogger(TitleValidateImplementation.class);

    private static final int LENGTH = 10;

//    @Override
    public void initialize(TitleValidate titleValidate) {
        logger.info("---------HIT validator1");

    }

//    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value.length() > LENGTH) {
            logger.error("Argument passed to TitleValidateImplementation not of type String");
            return false;
        }
        logger.info("Validation passed, returning TRUE.....");
        return true;
    }
}
