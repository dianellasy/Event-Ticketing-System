package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Booking;
import com.example.event_ticketing_system.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository for Booking entity
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Get all bookings for a specific attendee
    List<Booking> findByAttendeeId(Integer attendeeId);

    // Prevent duplicate booking references
    boolean checkIfDuplicateBookingReference(String bookingReference);

    // Revenue calculation: sum of ticket prices for confirmed bookings
    List<Booking> calculateRevenue(Integer eventId, PaymentStatus paymentStatus);
}
