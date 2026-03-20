package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {
    // Get all ticket types for a specific event
    List<TicketType> findByEventId(Integer eventId);
}
