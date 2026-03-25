package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.TicketTypeDTO;
import com.example.event_ticketing_system.entity.TicketType;
import com.example.event_ticketing_system.service.TicketTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-types")
@RequiredArgsConstructor
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    // POST /api/ticket-types?eventId=1
    @PostMapping
    public ResponseEntity<TicketTypeDTO> createTicketType(
            @Valid @RequestBody TicketType ticketType,
            @RequestParam Integer eventId) {
        TicketTypeDTO created = ticketTypeService.createTicketType(ticketType, eventId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}