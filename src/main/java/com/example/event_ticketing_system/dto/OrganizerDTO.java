package com.example.event_ticketing_system.dto;

import lombok.Data;

// DTO representing organizer information returned to the client
@Data
public class OrganizerDTO {
    // Unique identifier for the organizer
    private Integer organizerId;

    // Full name or company name
    private String name;

    // Contact email for organizer identification
    private String email;

    // Contact phone number
    private String phoneNumber;
}