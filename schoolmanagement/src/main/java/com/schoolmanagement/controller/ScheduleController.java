package com.schoolmanagement.controller;

import com.schoolmanagement.dto.ScheduleDTO;
import com.schoolmanagement.model.Schedule;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.repository.StudentRepository;
import com.schoolmanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final StudentRepository studentRepository;

    public ScheduleController(ScheduleService scheduleService, StudentRepository studentRepository) {
        this.scheduleService = scheduleService;
        this.studentRepository = studentRepository;
    }

    /**
     * Récupère les emplois du temps pour un étudiant donné.
     *
     * @param studentId ID de l'étudiant
     * @return Liste des emplois du temps de l'étudiant
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByStudent(@PathVariable Long studentId) {
        List<Schedule> schedules = scheduleService.getSchedulesByStudentId(studentId);
        List<ScheduleDTO> scheduleDTOs = schedules.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scheduleDTOs);
    }

    /**
     * Crée un nouvel emploi du temps.
     *
     * @param scheduleDTO Objet DTO contenant les informations de l'emploi du temps
     * @return L'emploi du temps créé
     */
    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        validateScheduleDTO(scheduleDTO);

        Schedule schedule = mapDTOToEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapEntityToDTO(savedSchedule));
    }

    /**
     * Met à jour un emploi du temps existant.
     *
     * @param id          ID de l'emploi du temps à mettre à jour
     * @param scheduleDTO Objet DTO contenant les nouvelles informations
     * @return L'emploi du temps mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        validateScheduleDTO(scheduleDTO);

        Schedule schedule = mapDTOToEntity(scheduleDTO);
        schedule.setId(id);
        Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);
        return ResponseEntity.ok(mapEntityToDTO(updatedSchedule));
    }

    /**
     * Supprime un emploi du temps par son ID.
     *
     * @param id ID de l'emploi du temps à supprimer
     * @return Confirmation de la suppression
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Valide les données de l'objet ScheduleDTO.
     *
     * @param scheduleDTO Objet DTO à valider
     */
    private void validateScheduleDTO(ScheduleDTO scheduleDTO) {
        if (scheduleDTO.getSubject() == null || scheduleDTO.getSubject().isEmpty()) {
            throw new IllegalArgumentException("La matière est obligatoire.");
        }
        if (scheduleDTO.getProfessor() == null || scheduleDTO.getProfessor().isEmpty()) {
            throw new IllegalArgumentException("Le nom du professeur est obligatoire.");
        }
        if (scheduleDTO.getDay() == null || scheduleDTO.getDay().isEmpty()) {
            throw new IllegalArgumentException("Le jour est obligatoire.");
        }
        if (scheduleDTO.getStartTime() == null) {
            throw new IllegalArgumentException("L'heure de début est obligatoire.");
        }
        if (scheduleDTO.getEndTime() == null) {
            throw new IllegalArgumentException("L'heure de fin est obligatoire.");
        }
        if (scheduleDTO.getStartTime().isAfter(scheduleDTO.getEndTime())) {
            throw new IllegalArgumentException("L'heure de début doit être avant l'heure de fin.");
        }
    }

    /**
     * Mappe une entité Schedule en DTO.
     *
     * @param schedule Entité Schedule
     * @return Objet DTO correspondant
     */
    private ScheduleDTO mapEntityToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setSubject(schedule.getSubject());
        dto.setProfessor(schedule.getProfessor());
        dto.setDay(schedule.getDay());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());
        dto.setStudentId(schedule.getStudent().getId());
        return dto;
    }

    /**
     * Mappe un DTO en entité Schedule, en associant l'étudiant.
     *
     * @param dto Objet DTO
     * @return Entité Schedule correspondante
     */
    private Schedule mapDTOToEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setSubject(dto.getSubject());
        schedule.setProfessor(dto.getProfessor());
        schedule.setDay(dto.getDay());
        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());

        // Associer l'étudiant
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Étudiant non trouvé avec l'ID : " + dto.getStudentId()));
        schedule.setStudent(student);

        return schedule;
    }
}
