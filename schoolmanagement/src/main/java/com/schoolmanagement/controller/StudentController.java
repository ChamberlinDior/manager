package com.schoolmanagement.controller;

import com.schoolmanagement.dto.StudentDTO;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.Tutor;
import com.schoolmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

/**
 * REST Controller for managing students in the school management system.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructor for injecting dependencies.
     *
     * @param studentService The student service.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Retrieve all students.
     *
     * @return A list of all students.
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * Retrieve a student by ID.
     *
     * @param id The ID of the student.
     * @return The student details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    /**
     * Create a new student.
     *
     * @param studentDTO The student data transfer object containing the details.
     * @return The created student.
     */
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDTO studentDTO) {
        Student student = mapDTOToStudent(studentDTO);
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    /**
     * Update an existing student.
     *
     * @param id         The ID of the student to update.
     * @param studentDTO The updated student data.
     * @return The updated student.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        Student existingStudent = studentService.getStudentById(id);
        updateStudentFromDTO(existingStudent, studentDTO);
        Student updatedStudent = studentService.saveStudent(existingStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Upload a photo for a student (multipart).
     *
     * @param id   The student ID.
     * @param file The photo file.
     * @return Success or error message.
     */
    @PostMapping(value = "/{id}/upload-photo", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile file) {
        try {
            Student student = studentService.getStudentById(id);
            student.setPhoto(file.getBytes());
            studentService.saveStudent(student);
            return ResponseEntity.ok("Photo uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading photo.");
        }
    }

    /**
     * Fetch a student's photo.
     *
     * @param id The student ID.
     * @return The photo as byte array or 404 if not found.
     */
    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student.getPhoto() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
                .body(student.getPhoto());
    }

    /**
     * Delete a student by ID.
     *
     * @param id The ID of the student to delete.
     * @return A no-content response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Map a StudentDTO to a Student entity.
     * Décodage Base64 si la photo est présente.
     */
    private Student mapDTOToStudent(StudentDTO dto) {
        // Créer le tutor
        Tutor tutor = new Tutor(
                dto.getTutorFirstName(),
                dto.getTutorLastName(),
                dto.getTutorProfession(),
                dto.getTutorContact(),
                dto.getTutorAddress()
        );

        // Créer l'entité Student
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setNationality(dto.getNationality());
        student.setLevel(dto.getLevel());
        student.setClassroom(dto.getClassroom());
        student.setField(dto.getField());
        student.setOptions(dto.getOptions());
        student.setTutor(tutor);
        student.setActive(true);

        // Décoder la photo en Base64, si présente
        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            student.setPhoto(decodeBase64(dto.getPhoto()));
        }

        return student;
    }

    /**
     * Update an existing student entity from a StudentDTO.
     * Inclut le décodage Base64 si la photo est mise à jour.
     */
    private void updateStudentFromDTO(Student student, StudentDTO dto) {
        if (dto.getFirstName() != null) {
            student.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            student.setLastName(dto.getLastName());
        }
        if (dto.getDateOfBirth() != null) {
            student.setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getGender() != null) {
            student.setGender(dto.getGender());
        }
        if (dto.getNationality() != null) {
            student.setNationality(dto.getNationality());
        }
        if (dto.getLevel() != null) {
            student.setLevel(dto.getLevel());
        }
        if (dto.getClassroom() != null) {
            student.setClassroom(dto.getClassroom());
        }
        if (dto.getField() != null) {
            student.setField(dto.getField());
        }
        if (dto.getOptions() != null) {
            student.setOptions(dto.getOptions());
        }

        // Mettre à jour la photo si fournie
        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            student.setPhoto(decodeBase64(dto.getPhoto()));
        }

        // Mettre à jour le tutor si nécessaire
        if (dto.getTutorFirstName() != null || dto.getTutorLastName() != null ||
                dto.getTutorProfession() != null || dto.getTutorContact() != null ||
                dto.getTutorAddress() != null) {

            Tutor tutor = student.getTutor();
            if (tutor == null) {
                tutor = new Tutor();
            }
            if (dto.getTutorFirstName() != null) tutor.setFirstName(dto.getTutorFirstName());
            if (dto.getTutorLastName() != null) tutor.setLastName(dto.getTutorLastName());
            if (dto.getTutorProfession() != null) tutor.setProfession(dto.getTutorProfession());
            if (dto.getTutorContact() != null) tutor.setContact(dto.getTutorContact());
            if (dto.getTutorAddress() != null) tutor.setAddress(dto.getTutorAddress());
            student.setTutor(tutor);
        }
    }

    /**
     * Méthode utilitaire pour décoder un String Base64 en tableau d'octets (byte[]).
     * Gère un éventuel préfixe "data:image/xxx;base64," si présent.
     *
     * @param base64String la chaîne Base64 complète.
     * @return le tableau d'octets décodé.
     */
    private byte[] decodeBase64(String base64String) {
        // Si la chaîne contient "data:image", on coupe juste après la virgule
        if (base64String.contains(",")) {
            base64String = base64String.substring(base64String.indexOf(",") + 1);
        }
        // Décodage
        return Base64.getDecoder().decode(base64String);
    }
}
