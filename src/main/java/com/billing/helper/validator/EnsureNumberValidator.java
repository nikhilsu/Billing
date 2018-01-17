package com.billing.helper.validator;

import com.billing.helper.constraint.EnsureNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnsureNumberValidator implements ConstraintValidator<EnsureNumber, Object> {

    @Override
    public void initialize(EnsureNumber constraintAnnotation) { }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Initialize it.
        String regex = "[0-9]+";
        String data = String.valueOf(value);
        return data.matches(regex);
    }
}