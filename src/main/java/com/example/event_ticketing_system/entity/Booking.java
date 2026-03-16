package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer booking_id;

    @Column(nullable = false, unique = true)
    private Integer booking_reference;

    private String booking_date;

    // Enum value
    private String payment_status;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticket_type;
}