package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.VenueDTO;
import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    // POST /api/venues
    @PostMapping
    public ResponseEntity<VenueDTO> createVenue(@RequestBody Venue venue) {
        VenueDTO created = venueService.createVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}