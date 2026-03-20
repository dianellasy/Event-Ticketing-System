package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Attendee Entity: a person who registers on the platform and books tickets to events
@Entity
@Table(name= "attendee")
@Data
public class Attendee {
    // Primary key for the attendee table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendee_id")
    private Integer attendeeId;

    // Full name of the attendee
    @Column(nullable = false)
    private String name;

    // Unique email address used for identification and booking lookups
    @Column(nullable = false, unique = true)
    private String email;

    // One attendee can have many bookings
    @OneToMany(mappedBy = "attendee")
    private List<Booking> bookings;
}