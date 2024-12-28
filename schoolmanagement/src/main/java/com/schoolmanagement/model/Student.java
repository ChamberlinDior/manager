package com.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    @Lob
    private byte[] photo;
    private String level;
    private String field;
    private String classroom;
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
}
