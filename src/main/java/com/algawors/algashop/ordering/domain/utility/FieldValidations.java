package com.algawors.algashop.ordering.domain.utility;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class FieldValidations {

    private FieldValidations() {
    }

    public static void requiresValidEmail(String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(String email, String errorMessage) {
        Objects.requireNonNull(email);
        if (email.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void requiresNonBlank(String value) {
        requiresNonBlank(value, "");
    }

    public static void requiresNonBlank(String value, String errorMessage) {
        Objects.requireNonNull(value, errorMessage);
        if (value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
