package com.schoolmanagement.service;

import com.schoolmanagement.model.Teacher;
import com.schoolmanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Base64;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher saveTeacher(Teacher teacher) {
        validateTeacher(teacher);
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    private void validateTeacher(Teacher teacher) {
        if (teacher.getFirstName() == null || teacher.getLastName() == null) {
            throw new IllegalArgumentException("First name and last name are required");
        }
        if (teacher.getSubject() == null) {
            throw new IllegalArgumentException("Subject is required");
        }
    }

    public void updateTeacherPhoto(Long id, byte[] photo) {
        Teacher teacher = getTeacherById(id);
        teacher.setPhoto(photo);
        teacherRepository.save(teacher);
    }
}
