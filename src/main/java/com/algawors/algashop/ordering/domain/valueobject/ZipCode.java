package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;
import com.algawors.algashop.ordering.domain.utility.FieldValidations;

public record ZipCode(String value) {

    public ZipCode {
        FieldValidations.requiresNonBlank(value, ErrorMessages.VALIDATION_ERROR_ZIPCODE_IS_BLANK_OR_NULL);

        if (value.length() != 5) {
            throw  new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_ZIPCODE_IS_INVALID);
        }
    }

    @Override
    public String toString() {
        return this.value;
    }
}
