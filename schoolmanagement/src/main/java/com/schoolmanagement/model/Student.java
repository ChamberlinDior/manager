package com.schoolmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entity representing a student in the school management system.
 */
@Entity
@Table(name = "students") // Explicit table naming for clarity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB", nullable = true)
    private byte[] photo;

    @Column(name = "level", nullable = false, length = 20) // E.g., Primary, Secondary, High School
    private String level;

    @Column(name = "classroom", nullable = false, length = 20) // E.g., Grade 6, Grade 12
    private String classroom;

    @Column(name = "field", length = 50) // E.g., Science, Literature
    private String field;

    @ElementCollection
    @CollectionTable(
            name = "student_options",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "option_name", nullable = false)
    private List<String> options;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "tutor_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "tutor_last_name")),
            @AttributeOverride(name = "profession", column = @Column(name = "tutor_profession")),
            @AttributeOverride(name = "contact", column = @Column(name = "tutor_contact")),
            @AttributeOverride(name = "address", column = @Column(name = "tutor_address"))
    })
    private Tutor tutor;

    // Getters and Setters with Javadoc for better documentation

    /**
     * Gets the ID of the student.
     *
     * @return the student ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id the student ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
