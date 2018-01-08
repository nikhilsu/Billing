package com.billing.helper.validator;

import com.billing.helper.Response;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelValidator {
    private Validator validator;

    @Autowired
    public ModelValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> Response validate(T model){
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        List<String> errors = new ArrayList<>();
        for(ConstraintViolation<T> violation : violations) {
            errors.add(violation.getMessage());
        }
        return errors.isEmpty() ? Response.SuccessEmptyPayload() : Response.Failure(errors);
    }
}
