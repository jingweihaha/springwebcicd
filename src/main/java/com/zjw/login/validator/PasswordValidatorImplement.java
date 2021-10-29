package com.zjw.login.validator;

import com.zjw.login.annotation.PasswordValidator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jingw
 * @created 10/26/2021 10:57 PM
 * @project login
 */
public class PasswordValidatorImplement implements ConstraintValidator<PasswordValidator, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if(s.contains("abc")){
            return false;
        }

        return true;
    }
}
