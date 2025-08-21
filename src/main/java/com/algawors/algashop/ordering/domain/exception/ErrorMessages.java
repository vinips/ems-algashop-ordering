package com.algawors.algashop.ordering.domain.exception;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST = "BirthDate must be a past date";
    public static final String VALIDATION_ERROR_BIRTHDATE_IS_NULL = "BirthDate cannot be null";
    public static final String VALIDATION_ERROR_FULLNAME_IS_NULL = "FullName cannot be null";
    public static final String VALIDATION_ERROR_FULLNAME_IS_BLANK = "FullName cannot be blank";
    public static final String VALIDATION_ERROR_FIRSTNAME_IS_BLANK = "First Name cannot be blank";
    public static final String VALIDATION_ERROR_LASTNAME_IS_BLANK = "Last Name cannot be blank";
    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "Email is invalid";
    public static final String VALIDATION_ERROR_POINTS_NEGATIVE = "Loyalty Points must be higher than 0";
    public static final String VALIDATION_ERROR_DOCUMENT_IS_BLANK_OR_NULL = "Document cannot be blank or null";
    public static final String VALIDATION_ERROR_PHONE_IS_BLANK_OR_NULL = "Phone cannot be blank or null";



    public static final String ERROR_CUSTOMER_ARCHIVED = "Customer is archived, it cannot be changed";

}
