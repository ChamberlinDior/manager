package com.schoolmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String position;
    private String category;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private int seniorityInMonths;
    private String cnssNumber;
    private String cnamgsNumber;
    private BigDecimal baseSalary;
    private int overtimeHours;
    private int absenceDays;
    private BigDecimal bonus;
    private BigDecimal netSalary;
    private String photo; // Base64 format

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getSeniorityInMonths() {
        return seniorityInMonths;
    }

    public void setSeniorityInMonths(int seniorityInMonths) {
        this.seniorityInMonths = seniorityInMonths;
    }

    public String getCnssNumber() {
        return cnssNumber;
    }

    public void setCnssNumber(String cnssNumber) {
        this.cnssNumber = cnssNumber;
    }

    public String getCnamgsNumber() {
        return cnamgsNumber;
    }

    public void setCnamgsNumber(String cnamgsNumber) {
        this.cnamgsNumber = cnamgsNumber;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public int getAbsenceDays() {
        return absenceDays;
    }

    public void setAbsenceDays(int absenceDays) {
        this.absenceDays = absenceDays;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
