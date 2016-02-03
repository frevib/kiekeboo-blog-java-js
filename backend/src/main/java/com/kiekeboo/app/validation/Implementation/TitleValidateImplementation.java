package com.kiekeboo.app.validation.Implementation;

import com.kiekeboo.app.validation.TitleValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleValidateImplementation implements ConstraintValidator<TitleValidate, String> {

    protected final Logger logger = LoggerFactory.getLogger(TitleValidateImplementation.class);

    public void initialize(TitleValidate titleValidate) {
        logger.info("Using TitleValidate.");
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        final int MAX_LENGTH = 50;
        final Pattern allowedCharacters = Pattern.compile("[\\w\\s.,?!]*");
        final Matcher matcher = allowedCharacters.matcher(value);

        // Check length
        if(value.length() > MAX_LENGTH) {
            logger.warn("Argument passed is longer than {} characters", MAX_LENGTH);
            return false;
        }

        // Check against allowed characters (could be done together with LENGTH with regex, but this is just a showcase)
        if(!matcher.matches()) {
            logger.warn("Title user input does not comply with allowed characters");
            return false;
        }

        logger.info("Title Validation passed.");
        return true;
    }
}
