package com.schoolmanagement.repository;

import com.schoolmanagement.model.ReportCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCardRepository extends JpaRepository<ReportCard, Long> {
    ReportCard findByStudentId(Long studentId);
}
