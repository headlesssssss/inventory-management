package com.example.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> Map<String, String> validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<T> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }

        return errors;
    }
}
