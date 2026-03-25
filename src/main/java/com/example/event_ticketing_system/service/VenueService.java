package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.VenueDTO;
import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    @Transactional
    public VenueDTO createVenue(Venue venue) {
        Venue saved = venueRepository.save(venue);
        return toDTO(saved);
    }

    // map venue entity to VenueDTO
    private VenueDTO toDTO(Venue venue) {
        VenueDTO dto = new VenueDTO();
        dto.setVenueId(venue.getVenueId());
        dto.setName(venue.getName());
        dto.setAddress(venue.getAddress());
        dto.setCity(venue.getCity());
        dto.setTotalCapacity(venue.getTotalCapacity());
        return dto;
    }
}