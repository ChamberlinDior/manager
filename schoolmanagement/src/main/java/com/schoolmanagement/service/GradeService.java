package com.schoolmanagement.service;

import com.schoolmanagement.model.Grade;
import com.schoolmanagement.model.ReportCard;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.repository.GradeRepository;
import com.schoolmanagement.repository.ReportCardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final ReportCardRepository reportCardRepository;
    private final StudentService studentService;

    public GradeService(GradeRepository gradeRepository, ReportCardRepository reportCardRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.reportCardRepository = reportCardRepository;
        this.studentService = studentService;
    }

    public Grade addGrade(Long studentId, Grade grade) {
        Student student = studentService.getStudentById(studentId);
        grade.setStudent(student);
        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public ReportCard generateReportCard(Long studentId) {
        Student student = studentService.getStudentById(studentId);
        List<Grade> grades = getGradesByStudent(studentId);

        double weightedSum = grades.stream()
                .mapToDouble(g -> g.getGrade() * g.getCoefficient())
                .sum();

        int totalCoefficients = grades.stream().mapToInt(Grade::getCoefficient).sum();
        double average = totalCoefficients == 0 ? 0 : weightedSum / totalCoefficients;

        ReportCard reportCard = new ReportCard();
        reportCard.setStudent(student);
        reportCard.setAverageGrade(average);
        reportCard.setGrades(grades);
        reportCard.setGeneratedAt(LocalDateTime.now());

        return reportCardRepository.save(reportCard);
    }
}
