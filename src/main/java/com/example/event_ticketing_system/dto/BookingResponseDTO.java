package com.example.event_ticketing_system.dto;

import com.example.event_ticketing_system.entity.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

// DTO representing booking details returned to the client
@Data
public class BookingResponseDTO {
    // Unique identifier for the booking
    private Integer bookingId;

    // Human-readable booking reference code
    private String bookingReference;

    // Timestamp of when the booking was made
    private LocalDateTime bookingDate;

    // Payment status (PENDING, CONFIRMED, CANCELLED)
    private PaymentStatus paymentStatus;

    // Name of the attendee who made the booking
    private String attendeeName;

    // Title of the event associated with the ticket
    private String eventTitle;

    // Tier name of the ticket type (e.g., VIP, General Admission, Student)
    private String ticketTypeName;

    // Price, in USD, of the ticket purchased
    private Double ticketPrice;
}