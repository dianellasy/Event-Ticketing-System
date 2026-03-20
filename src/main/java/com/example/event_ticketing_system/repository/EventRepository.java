package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Event;
import com.example.event_ticketing_system.entity.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository for Event entity
public interface EventRepository extends JpaRepository<Event, Integer> {
    // List all events with a given status (UPCOMING, ONGOING, CANCELLED, COMPLETED)
    List<Event> findByStatus(EventStatus status);
}