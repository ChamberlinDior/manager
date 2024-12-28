package com.schoolmanagement.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class Tutor {
    private String firstName;
    private String lastName;
    private String profession;
    private String contact;
    private String address;
}