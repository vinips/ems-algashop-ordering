package com.algawors.algashop.ordering.domain.entity;

import com.algawors.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algawors.algashop.ordering.domain.exception.ErrorMessages;
import com.algawors.algashop.ordering.domain.utility.FieldValidations;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


public class Customer {

    private UUID id;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String document;
    private String phone;
    private Boolean promotionNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private Integer loyaltyPoints;

    public Customer(UUID id, String fullName, LocalDate birthDate, String email, String document,
                    String phone, Boolean promotionNotificationsAllowed, OffsetDateTime registeredAt) {
        this.setId(id);
        this.setFullName(fullName);
        this.setBirthDate(birthDate);
        this.setEmail(email);
        this.setDocument(document);
        this.setPhone(phone);
        this.setPromotionNotificationsAllowed(promotionNotificationsAllowed);
        this.setRegisteredAt(registeredAt);
        this.setArchived(false);
        this.setloyaltyPoints(0);
    }

    public Customer(UUID id, String fullName, LocalDate birthDate, String email, String document,
                    String phone, Boolean promotionNotificationsAllowed, Boolean archived,
                    OffsetDateTime registeredAt, OffsetDateTime archivedAt, Integer loyaltyPoints) {

        this.setId(id);
        this.setFullName(fullName);
        this.setBirthDate(birthDate);
        this.setEmail(email);
        this.setDocument(document);
        this.setPhone(phone);
        this.setPromotionNotificationsAllowed(promotionNotificationsAllowed);
        this.setRegisteredAt(registeredAt);
        this.setArchived(archived);
        this.setArchivedAt(archivedAt);
        this.setloyaltyPoints(loyaltyPoints);
    }

    public void addLoyaltyPoints(Integer points) {
        //
    }


    public void archive() {
        verifyIfChangeable();
        this.setArchived(true);
        this.setArchivedAt(OffsetDateTime.now());
        this.setFullName("Anonymous");
        this.setEmail(UUID.randomUUID() + "@anonymous.com");
        this.setPhone("000-000-0000");
        this.setDocument("000-00-0000");
        this.setBirthDate(null);
        this.setPromotionNotificationsAllowed(false);
    }

    public void enablePromotionNotifications() {
        verifyIfChangeable();
        setPromotionNotificationsAllowed(true);
    }

    public void disablePromotionNotifications() {
        verifyIfChangeable();
        setPromotionNotificationsAllowed(false);
    }

    public void changeName(String name) {
        verifyIfChangeable();
        setFullName(name);
    }

    public void changeEmail(String email) {
        verifyIfChangeable();
        setEmail(email);
    }

    public void changePhone(String phone) {
        verifyIfChangeable();
        setPhone(phone);
    }

    public UUID id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public String email() {
        return email;
    }

    public String document() {
        return document;
    }

    public String phone() {
        return phone;
    }

    public Boolean isPromotionNotificationsAllowed() {
        return promotionNotificationsAllowed;
    }

    public Boolean isArchived() {
        return archived;
    }

    public OffsetDateTime registeredAt() {
        return registeredAt;
    }

    public OffsetDateTime archivedAt() {
        return archivedAt;
    }

    public Integer loyaltyPoints() {
        return loyaltyPoints;
    }

    private void setId(UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setFullName(String fullName) {
        Objects.requireNonNull(fullName, ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_NULL);
        if (fullName.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_FULLNAME_IS_BLANK);
        }
        this.fullName = fullName;
    }

    private void setBirthDate(LocalDate birthDate) {
        if (birthDate == null ) {
            this.birthDate = null;
            return;
        }
        if (birthDate.isAfter(LocalDate.now())) {
           throw new IllegalArgumentException(ErrorMessages.VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST);
        }

        this.birthDate = birthDate;
    }

    private void setEmail(String email) {
        FieldValidations.requiresValidEmail(email, ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID);
        this.email = email;
    }

    private void setDocument(String document) {
        Objects.requireNonNull(document);
        this.document = document;
    }

    private void setPhone(String phone) {
        Objects.requireNonNull(phone);
        this.phone = phone;
    }

    private void setPromotionNotificationsAllowed(Boolean promotionNotificationsAllowed) {
        Objects.requireNonNull(promotionNotificationsAllowed);
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
    }

    private void setArchived(Boolean archived) {
        Objects.requireNonNull(archived);
        this.archived = archived;
    }

    private void setRegisteredAt(OffsetDateTime registeredAt) {
        Objects.requireNonNull(registeredAt);
        this.registeredAt = registeredAt;
    }

    private void setArchivedAt(OffsetDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    private void setloyaltyPoints(Integer loyaltyPoints) {
        Objects.requireNonNull(loyaltyPoints);
        this.loyaltyPoints = loyaltyPoints;
    }

    private void verifyIfChangeable() {
        if (Boolean.TRUE.equals(this.isArchived())) {
            throw new CustomerArchivedException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
