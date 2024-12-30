package com.schoolmanagement.controller;

import com.schoolmanagement.dto.TeacherDTO;
import com.schoolmanagement.model.Teacher;
import com.schoolmanagement.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setSubject(teacherDTO.getSubject());
        teacher.setSalary(teacherDTO.getSalary());
        if (teacherDTO.getPhoto() != null && !teacherDTO.getPhoto().isEmpty()) {
            teacher.setPhoto(Base64.getDecoder().decode(teacherDTO.getPhoto()));
        }
        teacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherService.getTeacherById(id);
        existingTeacher.setFirstName(teacherDTO.getFirstName());
        existingTeacher.setLastName(teacherDTO.getLastName());
        existingTeacher.setSubject(teacherDTO.getSubject());
        existingTeacher.setSalary(teacherDTO.getSalary());
        if (teacherDTO.getPhoto() != null && !teacherDTO.getPhoto().isEmpty()) {
            existingTeacher.setPhoto(Base64.getDecoder().decode(teacherDTO.getPhoto()));
        }
        existingTeacher = teacherService.saveTeacher(existingTeacher);
        return ResponseEntity.ok(existingTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
