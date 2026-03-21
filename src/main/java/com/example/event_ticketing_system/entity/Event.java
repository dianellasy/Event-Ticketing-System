package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.example.event_ticketing_system.enums.EventStatus;

// Event Entity: the core entity of the system
// An event is created by one Organizer and held at one Venue
@Entity
@Table(name = "event")
@Data
public class Event {
    // Primary key for the event table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    // Title of the event
    @Column(nullable = false)
    private String title;

    // Optional full description of the event
    private String description;

    // Date and time of the event
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    // Enum representing event status (UPCOMING, ONGOING, CANCELLED, COMPLETED)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    // Foreign key to Organizer - many events belong to one organizer
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    // Foreign key to Venue - many events belong to one venue
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    // One event can have many ticket types
    @OneToMany(mappedBy = "event")
    private List<TicketType> ticketTypes;
}