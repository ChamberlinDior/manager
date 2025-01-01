package com.schoolmanagement.service;

import com.schoolmanagement.dto.EmployeeDTO;
import com.schoolmanagement.model.Employee;
import com.schoolmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setCategory(employeeDTO.getCategory());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setSeniorityInMonths(employeeDTO.getSeniorityInMonths());
        employee.setCnssNumber(employeeDTO.getCnssNumber());
        employee.setCnamgsNumber(employeeDTO.getCnamgsNumber());
        employee.setBaseSalary(employeeDTO.getBaseSalary());
        employee.setOvertimeHours(employeeDTO.getOvertimeHours());
        employee.setAbsenceDays(employeeDTO.getAbsenceDays());
        employee.setBonus(employeeDTO.getBonus());
        employee.setNetSalary(calculateNetSalary(employee));
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setCategory(employeeDTO.getCategory());
        employee.setBaseSalary(employeeDTO.getBaseSalary());
        employee.setOvertimeHours(employeeDTO.getOvertimeHours());
        employee.setAbsenceDays(employeeDTO.getAbsenceDays());
        employee.setBonus(employeeDTO.getBonus());
        employee.setNetSalary(calculateNetSalary(employee));
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private BigDecimal calculateNetSalary(Employee employee) {
        BigDecimal grossSalary = employee.getBaseSalary()
                .add(BigDecimal.valueOf(employee.getOvertimeHours() * 5000)) // Example for overtime
                .add(employee.getBonus() != null ? employee.getBonus() : BigDecimal.ZERO);
        BigDecimal deductions = grossSalary.multiply(BigDecimal.valueOf(0.15)); // Example 15% deductions
        return grossSalary.subtract(deductions);
    }
}
