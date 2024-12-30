package com.schoolmanagement.service;

import com.schoolmanagement.model.Schedule;
import com.schoolmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedulesByStudentId(Long studentId) {
        return scheduleRepository.findByStudentId(studentId);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule scheduleDetails) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        existingSchedule.setSubject(scheduleDetails.getSubject());
        existingSchedule.setProfessor(scheduleDetails.getProfessor());
        existingSchedule.setDay(scheduleDetails.getDay());
        existingSchedule.setStartTime(scheduleDetails.getStartTime());
        existingSchedule.setEndTime(scheduleDetails.getEndTime());
        return scheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
