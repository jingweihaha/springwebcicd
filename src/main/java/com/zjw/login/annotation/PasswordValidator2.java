package com.zjw.login.annotation;

import com.zjw.login.validator.PasswordValidatorImplement;
import com.zjw.login.validator.PasswordValidatorImplement2;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jingw
 * @created 10/27/2021 11:36 AM
 * @project login
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidatorImplement2.class)
public @interface PasswordValidator2 {
    String severity() default "low";
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
