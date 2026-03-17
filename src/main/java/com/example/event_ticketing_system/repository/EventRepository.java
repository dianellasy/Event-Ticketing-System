package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    // List all upcoming events
    List<Event> findByStatus(String status);
}
