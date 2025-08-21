package com.algawors.algashop.ordering.domain.entity;

import com.algawors.algashop.ordering.domain.exception.CustomerArchivedException;
import com.algawors.algashop.ordering.domain.valueobject.*;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


public class Customer {

    private CustomerId id;
    private FullName fullName;
    private BirthDate birthDate;
    private Email email;
    private Document document;
    private String phone;
    private Boolean promotionNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private LoyaltyPoints loyaltyPoints;

    @SuppressWarnings("squid:S107")
    public Customer(CustomerId id, FullName fullName, BirthDate birthDate, Email email, Document document,
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
        this.setLoyaltyPoints(LoyaltyPoints.ZERO);
    }

    @SuppressWarnings("squid:S107")
    public Customer(CustomerId id, FullName fullName, BirthDate birthDate, Email email, Document document,
                    String phone, Boolean promotionNotificationsAllowed, Boolean archived,
                    OffsetDateTime registeredAt, OffsetDateTime archivedAt, LoyaltyPoints loyaltyPoints) {

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
        this.setLoyaltyPoints(loyaltyPoints);
    }

    public void addLoyaltyPoints(LoyaltyPoints points) {
        verifyIfChangeable();
        this.setLoyaltyPoints(this.loyaltyPoints.add(points));
    }


    public void archive() {
        verifyIfChangeable();
        this.setArchived(true);
        this.setArchivedAt(OffsetDateTime.now());
        this.setFullName(new FullName("Anonymous", "Anonymous"));
        this.setEmail(new Email(UUID.randomUUID() + "@anonymous.com"));
        this.setPhone("000-000-0000");
        this.setDocument(new Document("000-00-0000"));
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

    public void changeName(FullName name) {
        verifyIfChangeable();
        setFullName(name);
    }

    public void changeEmail(Email email) {
        verifyIfChangeable();
        setEmail(email);
    }

    public void changePhone(String phone) {
        verifyIfChangeable();
        setPhone(phone);
    }

    public CustomerId id() {
        return id;
    }

    public FullName fullName() {
        return fullName;
    }

    public BirthDate birthDate() {
        return birthDate;
    }

    public Email email() {
        return email;
    }

    public Document document() {
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

    public LoyaltyPoints loyaltyPoints() {
        return loyaltyPoints;
    }

    private void setId(CustomerId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    private void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    private void setDocument(Document document) {
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

    private void setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
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
