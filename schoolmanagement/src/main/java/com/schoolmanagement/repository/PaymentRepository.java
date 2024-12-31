package com.schoolmanagement.repository;

import com.schoolmanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentId(Long studentId);
    List<Payment> findByMonthAndYear(String month, int year);
}
