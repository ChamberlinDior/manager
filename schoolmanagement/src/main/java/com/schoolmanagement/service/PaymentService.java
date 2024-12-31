package com.schoolmanagement.service;

import com.schoolmanagement.dto.PaymentDTO;
import com.schoolmanagement.model.Payment;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.repository.PaymentRepository;
import com.schoolmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    public Payment recordPayment(PaymentDTO paymentDTO) {
        Student student = studentRepository.findById(paymentDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setMonth(paymentDTO.getMonth());
        payment.setYear(paymentDTO.getYear());
        payment.setAmountPaid(paymentDTO.getAmountPaid());
        payment.setTotalDue(paymentDTO.getTotalDue());
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus(calculateStatus(paymentDTO.getAmountPaid(), paymentDTO.getTotalDue()));

        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByMonthAndYear(String month, int year) {
        return paymentRepository.findByMonthAndYear(month, year);
    }

    public Payment updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        existingPayment.setAmountPaid(paymentDTO.getAmountPaid());
        existingPayment.setTotalDue(paymentDTO.getTotalDue());
        existingPayment.setStatus(calculateStatus(paymentDTO.getAmountPaid(), paymentDTO.getTotalDue()));

        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private String calculateStatus(BigDecimal amountPaid, BigDecimal totalDue) {
        if (amountPaid.compareTo(totalDue) >= 0) {
            return "PAID";
        } else if (amountPaid.compareTo(BigDecimal.ZERO) > 0) {
            return "PARTIAL";
        } else {
            return "UNPAID";
        }
    }
}
