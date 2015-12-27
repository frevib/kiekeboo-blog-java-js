package com.kiekeboo.app.validation;


import com.kiekeboo.app.validation.Implementation.TitleValidateImplementation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Constraint(validatedBy = TitleValidateImplementation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleValidate {

    String message() default "...TitleValidate in action...";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
