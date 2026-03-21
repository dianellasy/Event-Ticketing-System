package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.OrganizerDTO;
import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    // POST /api/organizers
    @PostMapping
    public ResponseEntity<OrganizerDTO> createOrganizer(@RequestBody Organizer organizer) {
        OrganizerDTO created = organizerService.createOrganizer(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}