package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

// Venue Entity: the physical location where an event is held
// Capacity is critical -- it drives availability logic
@Entity
@Table(name = "venue")
@Data
public class Venue {
    // Primary key for the venue table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_id")
    private Integer venueId;

    // Name of the venue
    @Column(nullable = false)
    private String name;

    // Full street address of the venue
    @Column(nullable = false)
    private String address;

    // City where the venue is located
    @Column(nullable = false)
    private String city;

    // Maximum number of people the venue holds
    @Column(name = "total_capacity", nullable = false)
    private Integer totalCapacity;

    // One venue can host many events
    @OneToMany(mappedBy = "venue")
    private List<Event> events;
}