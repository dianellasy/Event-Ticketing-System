package com.example.event_ticketing_system.dto;

import com.example.event_ticketing_system.entity.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

// DTO representing booking details returned to the client
@Data
public class BookingDTO {
    // Unique identifier for the booking
    private Integer bookingId;

    // Human-readable booking reference code
    private String bookingReference;

    // Timestamp of when the booking was made
    private LocalDateTime bookingDate;

    // Payment status (PENDING, CONFIRMED, CANCELLED)
    private PaymentStatus paymentStatus;

    // ID of the attendee who made the booking
    private Integer attendeeId;

    // ID of the ticket type purchased
    private Integer ticketTypeId;
}
