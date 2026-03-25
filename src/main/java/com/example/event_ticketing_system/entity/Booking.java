package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.example.event_ticketing_system.enums.PaymentStatus;

// Booking (Junction Table): the junction table between Attendee and TicketType
// Represents the act of an attendee booking a specific ticket type
@Entity
@Table(name = "booking")
@Data
public class Booking {
    // Primary key for the booking table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    // Unique human-readable booking reference code
    @Column(name = "booking_reference", unique = true)
    private String bookingReference;

    // Timestamp of when the booking was made
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    // Enum representing payment status (PENDING, CONFIRMED, CANCELLED)
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    // Foreign key to Attendee: many bookings belong to one attendee
    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    // Foreign key to TicketType: many bookings belong to one ticket type
    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;
}