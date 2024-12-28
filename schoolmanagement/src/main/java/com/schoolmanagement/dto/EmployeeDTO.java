package com.schoolmanagement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String position;
    private double salary;
}