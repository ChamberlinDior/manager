package com.schoolmanagement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTO {
    private String firstName;
    private String lastName;
    private String subject;
    private double salary;
}