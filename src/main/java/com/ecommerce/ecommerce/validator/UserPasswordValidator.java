package com.ecommerce.ecommerce.validator;

import com.ecommerce.ecommerce.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * In this class we can add custom validation to check Input is correct or wrong
 * like check length of password or check password contain required character
 */
@Component
public class UserPasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        if(user.getPassword().length()<8) {
            errors.rejectValue("password", "Length",
                    "Password must be at least 8 character long");
        }


    }
}
