package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.EventResponseDTO;
import com.example.event_ticketing_system.dto.TicketTypeDTO;
import com.example.event_ticketing_system.entity.Event;
import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.entity.TicketType;
import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.enums.EventStatus;
import com.example.event_ticketing_system.repository.EventRepository;
import com.example.event_ticketing_system.repository.OrganizerRepository;
import com.example.event_ticketing_system.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;

    @Transactional
    public EventResponseDTO createEvent(Event event, Integer organizerId, Integer venueId) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new RuntimeException("Organizer not found with id: " + organizerId));

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found with id: " + venueId));

        event.setOrganizer(organizer);
        event.setVenue(venue);

        if (event.getStatus() == null) {
            event.setStatus(EventStatus.UPCOMING);
        }

        Event saved = eventRepository.save(event);
        return toDTO(saved);
    }

    public List<EventResponseDTO> getUpcomingEvents() {
        return eventRepository.findByStatus(EventStatus.UPCOMING)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return toDTO(event);
    }

    // map Event entity to EventResponseDTO
    private EventResponseDTO toDTO(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setEventId(event.getEventId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setStatus(event.getStatus());
        dto.setOrganizerName(event.getOrganizer().getName());
        dto.setVenueName(event.getVenue().getName());

        // map ticket types if present
        if (event.getTicketTypes() != null) {
            List<TicketTypeDTO> ticketDTOs = event.getTicketTypes()
                    .stream()
                    .map(this::toTicketTypeDTO)
                    .collect(Collectors.toList());
            dto.setTicketTypes(ticketDTOs);
        }

        return dto;
    }

    // map TicketType entity to TicketTypeDTO
    private TicketTypeDTO toTicketTypeDTO(TicketType ticketType) {
        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(ticketType.getTicketTypeId());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setQuantityAvailable(ticketType.getQuantityAvailable());
        return dto;
    }
}