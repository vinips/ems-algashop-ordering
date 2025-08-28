package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;
import com.algawors.algashop.ordering.domain.utility.FieldValidations;

public record Phone(String value) {

    @SuppressWarnings("squid:S6207")
    public Phone(String value) {
        FieldValidations.requiresNonBlank(value, ErrorMessages.VALIDATION_ERROR_PHONE_IS_BLANK_OR_NULL);

        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
