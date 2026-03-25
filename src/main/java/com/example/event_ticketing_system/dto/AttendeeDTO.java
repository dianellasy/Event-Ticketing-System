package com.example.event_ticketing_system.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

// DTO representing basic attendee information returned to the client
@Data
public class AttendeeDTO {
    // Unique identifier for the attendee
    private Integer attendeeId;

    // Full name of the attendee
    @NotBlank
    private String name;

    // Email address used for identification and booking lookups
    @NotBlank
    private String email;
}