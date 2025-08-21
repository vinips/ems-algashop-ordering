package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;
import com.algawors.algashop.ordering.domain.utility.FieldValidations;

public record Email(String value) {

    @SuppressWarnings("squid:S6207")
    public Email(String value) {
        FieldValidations.requiresValidEmail(value, ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
