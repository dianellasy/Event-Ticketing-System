package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Attendee entity
public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
}