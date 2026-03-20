package com.example.event_ticketing_system.service;
 
import com.example.event_ticketing_system.entity.Event;
import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.enums.EventStatus;
import com.example.event_ticketing_system.repository.EventRepository;
import com.example.event_ticketing_system.repository.OrganizerRepository;
import com.example.event_ticketing_system.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
 
@Service
@RequiredArgsConstructor
public class EventService {
 
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;
 
    @Transactional
    public Event createEvent(Event event, Integer organizerId, Integer venueId) {
        // find organizer by id or error if not found
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new RuntimeException("Organizer not found with id: " + organizerId));
 
        // find venue by id
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found with id: " + venueId));
        
        // link organizer to event
        event.setOrganizer(organizer);

        // link venue to event
        event.setVenue(venue);
 
        // event default set to upcoming if no status
        if (event.getStatus() == null) {
            event.setStatus(EventStatus.UPCOMING);
        }
 
        return eventRepository.save(event);
    }
    
    // upcoming status
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByStatus(EventStatus.UPCOMING);
    }
 
    // find event by id
    public Event getEventById(Integer id) {
        // error if event not found
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }
}