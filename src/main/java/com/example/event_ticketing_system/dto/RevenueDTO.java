package com.example.event_ticketing_system.dto;

import lombok.Data;

// DTO representing total confirmed revenue for a specific event
@Data
public class RevenueDTO {
    // Title of the event
    private String eventTitle;

    // Total revenue generated from confirmed bookings
    private Double totalRevenue;
}