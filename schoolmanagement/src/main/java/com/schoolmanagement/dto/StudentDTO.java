package com.schoolmanagement.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for Student.
 */
public class StudentDTO {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String level;
    private String classroom;
    private String field;
    private List<String> options;

    // Tutor details
    private String tutorFirstName;
    private String tutorLastName;
    private String tutorProfession;
    private String tutorContact;
    private String tutorAddress;

    // Photo (encoded as Base64 for transfer)
    private String photo;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getTutorFirstName() {
        return tutorFirstName;
    }

    public void setTutorFirstName(String tutorFirstName) {
        this.tutorFirstName = tutorFirstName;
    }

    public String getTutorLastName() {
        return tutorLastName;
    }

    public void setTutorLastName(String tutorLastName) {
        this.tutorLastName = tutorLastName;
    }

    public String getTutorProfession() {
        return tutorProfession;
    }

    public void setTutorProfession(String tutorProfession) {
        this.tutorProfession = tutorProfession;
    }

    public String getTutorContact() {
        return tutorContact;
    }

    public void setTutorContact(String tutorContact) {
        this.tutorContact = tutorContact;
    }

    public String getTutorAddress() {
        return tutorAddress;
    }

    public void setTutorAddress(String tutorAddress) {
        this.tutorAddress = tutorAddress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
