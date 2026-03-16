package com.example.event_ticketing_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="TicketType")
@Data
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_type_id;

    private String name;

    @Column(nullable = false)
    private Double price;

    private Integer quantity_available;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "ticket_type", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
