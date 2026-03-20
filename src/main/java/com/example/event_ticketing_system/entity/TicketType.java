package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// TicketType Entity: each event has multiple ticket tiers with different names and prices
// Ticket types belong to one event and track how many tickets are still available
@Entity
@Table(name = "ticket_type")
@Data
public class TicketType {
    // Primary key for the ticket_type table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_type_id")
    private Integer ticketTypeId;

    // Tier name of the ticket type (e.g., VIP, General Admission, Student)
    private String name;

    // Price, in USD, of the ticket (must be >= 0)
    @Column(nullable = false)
    private Double price;

    // Remaining number of tickets available
    @Column(name = "quantity_available")
    private Integer quantityAvailable;

    // Foreign key to Event - many ticket types belong to one event
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // One ticket type can have many bookings
    @OneToMany(mappedBy = "ticketType")
    private List<Booking> bookings;
}