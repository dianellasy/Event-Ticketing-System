package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Venue entity
public interface VenueRepository extends JpaRepository<Venue, Integer> {
}