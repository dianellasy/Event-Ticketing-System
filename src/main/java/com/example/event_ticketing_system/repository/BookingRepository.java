package com.example.event_ticketing_system.repository;

import com.example.event_ticketing_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Get all bookings for an attendee
    List<Booking> findByAttendee_id(Integer attendee_id);

    // Get total revenue for an event
    @Query("""
        SELECT COALESCE(SUM(t.price), 0)
        FROM Booking b
        INNER JOIN b.ticket_type t
        WHERE t.event.event_id = :event_id
          AND b.payment_status = 'CONFIRMED'
    """)
    Double getRevenueByEvent(Integer event_id);
}
