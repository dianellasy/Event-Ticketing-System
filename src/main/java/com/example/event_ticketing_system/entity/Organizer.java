package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Organizer Entity: a person or company that creates and manages events
// Examples include Live Nation, a university club, or an independent promoter
@Entity
@Table(name = "organizer")
@Data
public class Organizer {
    // Primary key for the organizer table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizerId;

    // Full name or company name
    @Column(nullable = false)
    private String name;

    // Unique contact email for organizer identification
    @Column(nullable = false, unique = true)
    private String email;

    // Optional contact phone number
    private String phoneNumber;

    // One organizer can create many events
    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
}
