package com.schoolmanagement.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Tutor {

    private String firstName;
    private String lastName;
    private String profession;
    private String contact;
    private String address;

    // Constructors
    public Tutor() {
    }

    public Tutor(String firstName, String lastName, String profession, String contact, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
        this.contact = contact;
        this.address = address;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
