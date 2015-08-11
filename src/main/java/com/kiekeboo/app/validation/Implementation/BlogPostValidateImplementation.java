package com.kiekeboo.app.validation.Implementation;

import com.kiekeboo.app.model.BlogPost;
import com.kiekeboo.app.validation.TitleValidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class BlogPostValidateImplementation implements ConstraintValidator<TitleValidate, Object> {

    Logger logger = LoggerFactory.getLogger(BlogPostValidateImplementation.class);

    public void initialize(TitleValidate titleValidate) {

    }

    public boolean isValid(Object blogpostmodel, ConstraintValidatorContext constraintValidatorContext) {

        if(!(blogpostmodel instanceof BlogPost)) {
            logger.error("VALIDATION ERROR: not an instance of BlogPost");
        }

        BlogPost blogPost = (BlogPost) blogpostmodel;



        return false;
    }
}
