package com.example.event_ticketing_system.service;
 
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
    public Attendee createAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }
 
    public Attendee getAttendeeById(Integer id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + id));
    }
}