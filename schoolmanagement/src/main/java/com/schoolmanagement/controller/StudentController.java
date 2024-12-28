package com.schoolmanagement.controller;

import com.schoolmanagement.model.Student;
import com.schoolmanagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student existingStudent = studentService.getStudentById(id);

        // Mise à jour des champs
        if (updatedStudent.getFirstName() != null) {
            existingStudent.setFirstName(updatedStudent.getFirstName());
        }
        if (updatedStudent.getLastName() != null) {
            existingStudent.setLastName(updatedStudent.getLastName());
        }
        if (updatedStudent.getDateOfBirth() != null) {
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        }
        if (updatedStudent.getGender() != null) {
            existingStudent.setGender(updatedStudent.getGender());
        }
        if (updatedStudent.getNationality() != null) {
            existingStudent.setNationality(updatedStudent.getNationality());
        }
        if (updatedStudent.getLevel() != null) {
            existingStudent.setLevel(updatedStudent.getLevel());
        }
        if (updatedStudent.getClassroom() != null) {
            existingStudent.setClassroom(updatedStudent.getClassroom());
        }
        if (updatedStudent.getField() != null) {
            existingStudent.setField(updatedStudent.getField());
        }
        if (updatedStudent.getOptions() != null) {
            existingStudent.setOptions(updatedStudent.getOptions());
        }
        if (updatedStudent.getPhoto() != null) {
            existingStudent.setPhoto(updatedStudent.getPhoto());
        }
        if (updatedStudent.getTutor() != null) {
            existingStudent.setTutor(updatedStudent.getTutor());
        }

        Student savedStudent = studentService.saveStudent(existingStudent);
        return ResponseEntity.ok(savedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
