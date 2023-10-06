package com.book.model;

import java.io.Serializable;

// Represents an address with various attributes.
public class Address implements Serializable {

    // First line of the address.
    private String addressLine1;

    // Second line of the address.
    private String addressLine2;

    // City of the address.
    private String city;

    // State of the address.
    private String state;

    // Country of the address.
    private String country;

    // PIN code of the address.
    private long pinCode;

    // Phone number associated with the address.
    private String phone;

    // Getter method for addressLine1.
    public String getAddressLine1() {
        return addressLine1;
    }

    // Setter method for addressLine1.
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    // Getter method for addressLine2.
    public String getAddressLine2() {
        return addressLine2;
    }

    // Setter method for addressLine2.
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    // Getter method for city.
    public String getCity() {
        return city;
    }

    // Setter method for city.
    public void setCity(String city) {
        this.city = city;
    }

    // Getter method for state.
    public String getState() {
        return state;
    }

    // Setter method for state.
    public void setState(String state) {
        this.state = state;
    }

    // Getter method for country.
    public String getCountry() {
        return country;
    }

    // Setter method for country.
    public void setCountry(String country) {
        this.country = country;
    }

    // Getter method for pinCode.
    public long getPinCode() {
        return pinCode;
    }

    // Setter method for pinCode.
    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }

    // Getter method for phone.
    public String getPhone() {
        return phone;
    }

    // Setter method for phone.
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
