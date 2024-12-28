package com.schoolmanagement.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality; // Nouvelle propriété
    private String level; // Niveau scolaire
    private String classroom; // Classe
    private String field; // Filière
    private List<String> options; // Options choisies
}
