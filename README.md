# Event Ticketing System
A RESTful backend API for managing events, venues, organizers, attendees, ticket types, and bookings.

This project demonstrates full 3-layer Spring Boot architecture, JPA entity relationships, DTO mapping, business logic, and PostgreSQL integration.

## Team
| Name                 |       CWID       |
|:---------------------|:----------------:|
| Dianella Sy          |    884931890     |
| Saloni Joshi         |    885584714     |
| Siddharth Vasu       |    885505578     |

## Demo Video

## Postman Screenshots
### Create a new organizer:
![POST api/organizers](screenshots/POST_organizers.png)

### Create a new venue:
![POST api/venues](screenshots/POST_venues.png)

### Create a new event:
![POST api/events](screenshots/POST_events.png)

### Create a new ticket type:
![POST api/ticket-types](screenshots/POST_ticket-types.png)

### List all upcoming events:
![GET api/events](screenshots/GET_events.png)

### Get event details with ticket types:
![GET api/events/{id}](screenshots/GET_events_id.png)

### Register a new attendee:
![POST api/attendees](screenshots/POST_attendees.png)

### Book a ticket:
![POST api/bookings](screenshots/POST_bookings.png)

### Cancel a booking:
![PUT api/bookings/{id}/cancel](screenshots/PUT_bookings.png)

### Get total revenue for an event:
![GET api/events/{id}/revenue](screenshots/GET_events_id_revenue.png)

### Get all bookings for an attendee events:
![GET api/attendees/{id}/bookings](screenshots/GET_attendees_id_bookings.png)

## Documentation of the API