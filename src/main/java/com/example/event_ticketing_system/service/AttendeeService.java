package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.AttendeeDTO;
import com.example.event_ticketing_system.entity.Attendee;
import com.example.event_ticketing_system.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Transactional
    public AttendeeDTO createAttendee(Attendee attendee) {
        Attendee saved = attendeeRepository.save(attendee);
        return toDTO(saved);
    }

    public Attendee getAttendeeById(Integer id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + id));
    }

    // map attendee entity to AttendeeDTO
    private AttendeeDTO toDTO(Attendee attendee) {
        AttendeeDTO dto = new AttendeeDTO();
        dto.setAttendeeId(attendee.getAttendeeId());
        dto.setName(attendee.getName());
        dto.setEmail(attendee.getEmail());
        return dto;
    }
}