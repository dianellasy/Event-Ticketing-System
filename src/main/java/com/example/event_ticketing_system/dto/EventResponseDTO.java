package com.example.event_ticketing_system.dto;

import com.example.event_ticketing_system.enums.EventStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// DTO representing full event details returned to the client
@Data
public class EventResponseDTO {
    // Unique identifier for the event
    private Integer eventId;

    // Title of the event
    @NotBlank
    private String title;

    // Optional description of the event
    private String description;

    // Date and time when the event occurs
    @NotBlank
    private LocalDateTime eventDate;

    // Status of the event (UPCOMING, ONGOING, CANCELLED, COMPLETED)
    private EventStatus status;

    // Name of the organizer who created the event
    private String organizerName;

    // Name of the venue where the event is hosted
    private String venueName;

    // List of ticket types available for this event
    private List<TicketTypeDTO> ticketTypes;
}
