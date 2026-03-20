package com.example.event_ticketing_system.dto;

import lombok.Data;

// DTO representing venue information returned to the client
@Data
public class VenueDTO {
    // Unique identifier for the venue
    private Integer venueId;

    // Name of the venue
    private String name;

    // Full street address of the venue
    private String address;

    // City where the venue is located
    private String city;

    // Maximum number of people the venue holds
    private Integer totalCapacity;
}