package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.AttendeeBookingsDTO;
import com.example.event_ticketing_system.dto.AttendeeDTO;
import com.example.event_ticketing_system.entity.Attendee;
import com.example.event_ticketing_system.service.AttendeeService;
import com.example.event_ticketing_system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;
    private final BookingService bookingService;

    // POST /api/attendees
    @PostMapping
    public ResponseEntity<AttendeeDTO> createAttendee(@RequestBody Attendee attendee) {
        AttendeeDTO created = attendeeService.createAttendee(attendee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/attendees/{id}/bookings
    @GetMapping("/{id}/bookings")
    public ResponseEntity<AttendeeBookingsDTO> getBookingsForAttendee(@PathVariable Integer id) {
        AttendeeBookingsDTO bookings = bookingService.getBookingsByAttendee(id);
        return ResponseEntity.ok(bookings);
    }
}