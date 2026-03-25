package com.example.event_ticketing_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO representing organizer information returned to the client
@Data
public class OrganizerDTO {
    // Unique identifier for the organizer
    private Integer organizerId;

    // Full name or company name
    @NotBlank
    private String name;

    // Contact email for organizer identification
    @NotBlank
    private String email;

    // Contact phone number
    private String phoneNumber;
}