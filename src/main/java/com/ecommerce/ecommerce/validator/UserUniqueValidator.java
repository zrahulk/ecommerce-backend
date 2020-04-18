package com.ecommerce.ecommerce.validator;



import com.ecommerce.ecommerce.customAnnotaions.Unique;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserUniqueValidator implements ConstraintValidator<Unique,String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(Unique unique) {
        unique.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (userService != null && userService.existByEmail(email)) {
            return false;
        }
        return true;
    }
}
