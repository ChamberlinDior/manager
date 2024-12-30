package com.schoolmanagement.service;

import com.schoolmanagement.model.Student;
import com.schoolmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student saveStudent(Student student) {
        validateStudent(student);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void updateStudentPhoto(Long id, byte[] photo) {
        Student student = getStudentById(id);
        student.setPhoto(photo);
        studentRepository.save(student);
    }

    private void validateStudent(Student student) {
        if (student.getFirstName() == null || student.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name are required");
        }
        if (student.getNationality() == null) {
            throw new IllegalArgumentException("Nationality is required");
        }
    }
}
