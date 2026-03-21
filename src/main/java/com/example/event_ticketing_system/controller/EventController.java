package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.EventResponseDTO;
import com.example.event_ticketing_system.dto.RevenueDTO;
import com.example.event_ticketing_system.entity.Event;
import com.example.event_ticketing_system.service.BookingService;
import com.example.event_ticketing_system.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final BookingService bookingService;

    // POST /api/events
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody Event event,
            @RequestParam Integer organizerId,
            @RequestParam Integer venueId) {
        EventResponseDTO created = eventService.createEvent(event, organizerId, venueId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /api/events
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    // GET /api/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // GET /api/events/{id}/revenue
    @GetMapping("/{id}/revenue")
    public ResponseEntity<RevenueDTO> getRevenue(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getRevenueByEvent(id));
    }
}