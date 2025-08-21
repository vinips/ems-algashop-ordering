package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;

import java.util.Objects;

public record Phone(String value) {

    @SuppressWarnings("squid:S6207")
    public Phone(String value) {
        Objects.requireNonNull(value, ErrorMessages.VALIDATION_ERROR_PHONE_IS_BLANK_OR_NULL);

        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_PHONE_IS_BLANK_OR_NULL);
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
