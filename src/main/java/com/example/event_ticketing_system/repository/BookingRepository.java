package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Booking;
import com.example.event_ticketing_system.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Repository for Booking entity
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Get all bookings for a specific attendee
    List<Booking> findByAttendeeAttendeeId(Integer attendeeId);

    // Check if a booking reference already exists
    boolean existsByBookingReference(String bookingReference);

    // Check if an attendee already booked a specific ticket type
    boolean existsByAttendeeAttendeeIdAndTicketTypeTicketTypeId(Integer attendeeId, Integer ticketTypeId);

    // rev calc
    @Query("SELECT COALESCE(SUM(tt.price), 0) FROM Booking b " +
            "JOIN b.ticketType tt " +
            "WHERE tt.event.eventId = :eventId AND b.paymentStatus = :status")
    Double calculateRevenueByEventId(@Param("eventId") Integer eventId,
                                     @Param("status") PaymentStatus status);
}