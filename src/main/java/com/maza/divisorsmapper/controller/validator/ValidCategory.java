package com.maza.divisorsmapper.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MappingCategoryValidator.class)
@Target({ ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {

    String message() default "Invalid mapping category";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
