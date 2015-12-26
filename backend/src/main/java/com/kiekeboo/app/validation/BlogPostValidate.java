package com.kiekeboo.app.validation;


import com.kiekeboo.app.validation.Implementation.TitleValidateImplementation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Constraint(validatedBy = TitleValidateImplementation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface BlogPostValidate {
}
