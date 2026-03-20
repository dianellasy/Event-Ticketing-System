package com.example.event_ticketing_system.service;
 
import com.example.event_ticketing_system.entity.Attendee;
import com.example.event_ticketing_system.entity.Booking;
import com.example.event_ticketing_system.entity.TicketType;
import com.example.event_ticketing_system.enums.PaymentStatus;
import com.example.event_ticketing_system.repository.AttendeeRepository;
import com.example.event_ticketing_system.repository.BookingRepository;
import com.example.event_ticketing_system.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.time.LocalDateTime;
import java.util.List;
 
@Service
@RequiredArgsConstructor
public class BookingService {
 
    private final BookingRepository bookingRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketTypeRepository ticketTypeRepository;
 
    // POST /api/bookings book ticky
    @Transactional
    public Booking createBooking(Integer attendeeId, Integer ticketTypeId) {
 
        // check ticket type exists and has availability
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found with id: " + ticketTypeId));
 
        if (ticketType.getQuantityAvailable() <= 0) {
            throw new RuntimeException("Sorry, this ticket type is sold out.");
        }
 
        // check attendee exists
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));
 
        // check attendee hasn't already booked ticket type
        boolean alreadyBooked = bookingRepository
                .existsByAttendeeAttendeeIdAndTicketTypeTicketTypeId(attendeeId, ticketTypeId);
        if (alreadyBooked) {
            throw new RuntimeException("You have already booked this ticket type.");
        }
 
        // decrement quantity available
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);
 
        // create and save the booking
        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);
        // temporary ref
        booking.setBookingReference("TKT-TEMP");
        Booking saved = bookingRepository.save(booking);
 
        // generate booking reference using the real booking ID: TKT-{year}-{zero-padded id}
        String year = String.valueOf(saved.getBookingDate().getYear());
        String paddedId = String.format("%05d", saved.getBookingId());
        saved.setBookingReference("TKT-" + year + "-" + paddedId);
        return bookingRepository.save(saved);
    }
 
    // PUT /api/bookings/{id}/cancel
    @Transactional
    public Booking cancelBooking(Integer bookingId) {
 
        // verify booking exists and is not already cancelled
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
 
        if (booking.getPaymentStatus() == PaymentStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled.");
        }
 
        // set status to cancelled
        booking.setPaymentStatus(PaymentStatus.CANCELLED);
 
        // restore ticket inventory
        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);
        ticketTypeRepository.save(ticketType);
 
        return bookingRepository.save(booking);
    }
 
    // GET /api/attendees/{id}/bookings
    public List<Booking> getBookingsByAttendee(Integer attendeeId) {
        attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));
        return bookingRepository.findByAttendeeAttendeeId(attendeeId);
    }
 
    // GET /api/events/{id}/revenue
    public Double getRevenueByEvent(Integer eventId) {
        return bookingRepository.calculateRevenueByEventId(eventId, PaymentStatus.CONFIRMED);
    }
}