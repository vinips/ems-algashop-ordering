package com.algawors.algashop.ordering.domain.valueobject;

import com.algawors.algashop.ordering.domain.exception.ErrorMessages;

import java.util.Objects;

public record FullName(String firstName, String lastName) {
    public FullName(String firstName, String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);

        if (firstName.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_FIRSTNAME_IS_BLANK);
        }
        if (lastName.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_LASTNAME_IS_BLANK);
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
