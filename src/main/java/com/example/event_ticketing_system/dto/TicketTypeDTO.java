package com.example.event_ticketing_system.dto;

import lombok.Data;

// DTO representing a ticket tier for an event
@Data
public class TicketTypeDTO {
    // Unique identifier for the ticket type
    private Integer ticketTypeId;

    // Tier name of the ticket type (e.g., VIP, General Admission, Student)
    private String name;

    // Price, in USD, of the ticket (must be >= 0)
    private Double price;

    // Remaining number of tickets available
    private Integer quantityAvailable;
}