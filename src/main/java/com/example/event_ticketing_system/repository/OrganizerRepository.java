package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Organizer entity
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}