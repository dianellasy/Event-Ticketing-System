package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.OrganizerDTO;
import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Transactional
    public OrganizerDTO createOrganizer(Organizer organizer) {
        Organizer saved = organizerRepository.save(organizer);
        return toDTO(saved);
    }

    // map organizer entity to OrganizerDTO
    private OrganizerDTO toDTO(Organizer organizer) {
        OrganizerDTO dto = new OrganizerDTO();
        dto.setOrganizerId(organizer.getOrganizerId());
        dto.setName(organizer.getName());
        dto.setEmail(organizer.getEmail());
        dto.setPhoneNumber(organizer.getPhoneNumber());
        return dto;
    }
}