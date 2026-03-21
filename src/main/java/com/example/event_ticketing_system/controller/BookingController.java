package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.BookingResponseDTO;
import com.example.event_ticketing_system.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // POST /api/bookings
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestParam Integer attendeeId,
            @RequestParam Integer ticketTypeId) {
        BookingResponseDTO booking = bookingService.createBooking(attendeeId, ticketTypeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    // PUT /api/bookings/{id}/cancel
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Integer id) {
        BookingResponseDTO cancelled = bookingService.cancelBooking(id);
        return ResponseEntity.ok(cancelled);
    }
}