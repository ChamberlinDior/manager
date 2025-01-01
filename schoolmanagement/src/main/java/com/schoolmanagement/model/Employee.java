package com.schoolmanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "position", nullable = false, length = 100)
    private String position;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "seniority_in_months", nullable = false)
    private int seniorityInMonths;

    @Column(name = "cnss_number", nullable = false, length = 20)
    private String cnssNumber;

    @Column(name = "cnamgs_number", nullable = false, length = 20)
    private String cnamgsNumber;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    @Column(name = "base_salary", nullable = false)
    private BigDecimal baseSalary;

    @Column(name = "overtime_hours", nullable = false)
    private int overtimeHours;

    @Column(name = "absence_days", nullable = false)
    private int absenceDays;

    @Column(name = "bonus", nullable = true)
    private BigDecimal bonus;

    @Column(name = "net_salary", nullable = true)
    private BigDecimal netSalary;

    // Getters and Setters
    public Long getId() {
        return id;
    }

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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
}
