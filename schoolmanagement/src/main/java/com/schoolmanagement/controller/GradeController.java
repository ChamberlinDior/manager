package com.schoolmanagement.controller;

import com.schoolmanagement.dto.GradeDTO;
import com.schoolmanagement.model.Grade;
import com.schoolmanagement.model.ReportCard;
import com.schoolmanagement.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/{studentId}/add")
    public ResponseEntity<Grade> addGrade(@PathVariable Long studentId, @RequestBody GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setSubject(gradeDTO.getSubject());
        grade.setCoefficient(gradeDTO.getCoefficient());
        grade.setGrade(gradeDTO.getGrade());
        grade.setType(gradeDTO.getType());

        Grade savedGrade = gradeService.addGrade(studentId, grade);
        return ResponseEntity.ok(savedGrade);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getGradesByStudent(studentId));
    }

    @PostMapping("/{studentId}/generate-report-card")
    public ResponseEntity<ReportCard> generateReportCard(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.generateReportCard(studentId));
    }
}
