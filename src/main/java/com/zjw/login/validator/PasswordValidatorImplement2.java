package com.zjw.login.validator;

import com.zjw.login.annotation.PasswordValidator2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jingw
 * @created 10/27/2021 11:38 AM
 * @project login
 */
public class PasswordValidatorImplement2 implements ConstraintValidator<PasswordValidator2, String> {

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.contains("update"))
            return true;
        return false;
    }
}
