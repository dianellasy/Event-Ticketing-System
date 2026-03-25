package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.TicketTypeDTO;
import com.example.event_ticketing_system.entity.Event;
import com.example.event_ticketing_system.entity.TicketType;
import com.example.event_ticketing_system.repository.EventRepository;
import com.example.event_ticketing_system.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    @Transactional
    public TicketTypeDTO createTicketType(TicketType ticketType, Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        ticketType.setEvent(event);
        TicketType saved = ticketTypeRepository.save(ticketType);
        return toDTO(saved);
    }

    private TicketTypeDTO toDTO(TicketType ticketType) {
        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(ticketType.getTicketTypeId());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setQuantityAvailable(ticketType.getQuantityAvailable());
        return dto;
    }
}