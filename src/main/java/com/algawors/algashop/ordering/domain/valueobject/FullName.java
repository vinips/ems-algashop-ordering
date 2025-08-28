package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;
import com.algawors.algashop.ordering.domain.utility.FieldValidations;

public record FullName(String firstName, String lastName) {
    public FullName(String firstName, String lastName) {
        FieldValidations.requiresNonBlank(firstName, ErrorMessages.VALIDATION_ERROR_FIRSTNAME_IS_BLANK);
        FieldValidations.requiresNonBlank(lastName, ErrorMessages.VALIDATION_ERROR_LASTNAME_IS_BLANK);

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
