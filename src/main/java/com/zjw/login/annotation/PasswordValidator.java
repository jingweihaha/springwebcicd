package com.zjw.login.annotation;

import com.zjw.login.validator.PasswordValidatorImplement;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jingw
 * @created 10/26/2021 10:51 PM
 * @project login
 */
@Constraint(validatedBy = PasswordValidatorImplement.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PasswordValidator {
    String severity() default "low";
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
