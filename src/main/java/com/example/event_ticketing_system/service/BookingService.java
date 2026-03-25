package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.AttendeeBookingsDTO;
import com.example.event_ticketing_system.dto.BookingResponseDTO;
import com.example.event_ticketing_system.dto.RevenueDTO;
import com.example.event_ticketing_system.entity.Attendee;
import com.example.event_ticketing_system.entity.Booking;
import com.example.event_ticketing_system.entity.TicketType;
import com.example.event_ticketing_system.enums.PaymentStatus;
import com.example.event_ticketing_system.repository.AttendeeRepository;
import com.example.event_ticketing_system.repository.BookingRepository;
import com.example.event_ticketing_system.repository.EventRepository;
import com.example.event_ticketing_system.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    // POST /api/bookings
    @Transactional
    public BookingResponseDTO createBooking(Integer attendeeId, Integer ticketTypeId) {

        // check ticket type exists and has availability
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found with id: " + ticketTypeId));

        if (ticketType.getQuantityAvailable() <= 0) {
            throw new RuntimeException("Sorry, this ticket type is sold out.");
        }

        // check attendee exists
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));

        // check attendee hasn't already booked this ticket type
        boolean alreadyBooked = bookingRepository
                .existsByAttendeeAttendeeIdAndTicketTypeTicketTypeId(attendeeId, ticketTypeId);
        if (alreadyBooked) {
            throw new RuntimeException("You have already booked this ticket type.");
        }

        // decrement quantity available
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);

        // create and save booking with temp reference
        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);
        booking.setBookingReference("TKT-TEMP");
        Booking saved = bookingRepository.save(booking);

        // generate booking reference: TKT-{year}-{zero-padded id}
        String year = String.valueOf(saved.getBookingDate().getYear());
        String paddedId = String.format("%05d", saved.getBookingId());
        saved.setBookingReference("TKT-" + year + "-" + paddedId);
        Booking finalBooking = bookingRepository.save(saved);

        return toDTO(finalBooking);
    }

    // PUT /api/bookings/{id}/cancel
    @Transactional
    public BookingResponseDTO cancelBooking(Integer bookingId) {

        // 1. Verify booking exists and is not already cancelled
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        if (booking.getPaymentStatus() == PaymentStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled.");
        }

        // 2. Set status to cancelled
        booking.setPaymentStatus(PaymentStatus.CANCELLED);

        // restore ticket inventory
        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);
        ticketTypeRepository.save(ticketType);

        return toDTO(bookingRepository.save(booking));
    }

    // GET /api/attendees/{id}/bookings
    public AttendeeBookingsDTO getBookingsByAttendee(Integer attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found with id: " + attendeeId));

        List<BookingResponseDTO> bookingDTOs = bookingRepository
                .findByAttendeeAttendeeId(attendeeId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        AttendeeBookingsDTO dto = new AttendeeBookingsDTO();
        dto.setAttendeeId(attendee.getAttendeeId());
        dto.setName(attendee.getName());
        dto.setBookings(bookingDTOs);
        return dto;
    }

    // GET /api/events/{id}/revenue
    public RevenueDTO getRevenueByEvent(Integer eventId) {
        // Verify event exists
        String eventTitle = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with id: " + eventId))
                .getTitle();

        Double revenue = bookingRepository.calculateRevenueByEventId(eventId, PaymentStatus.CONFIRMED);

        RevenueDTO dto = new RevenueDTO();
        dto.setEventTitle(eventTitle);
        dto.setTotalRevenue(revenue);
        return dto;
    }

    // map booking entity to BookingResponseDTO
    private BookingResponseDTO toDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setBookingReference(booking.getBookingReference());
        dto.setBookingDate(booking.getBookingDate());
        dto.setPaymentStatus(booking.getPaymentStatus());
        dto.setAttendeeName(booking.getAttendee().getName());
        dto.setEventTitle(booking.getTicketType().getEvent().getTitle());
        dto.setTicketTypeName(booking.getTicketType().getName());
        dto.setTicketPrice(booking.getTicketType().getPrice());
        return dto;
    }
}