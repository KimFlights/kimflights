WITH const AS (
    SELECT
        'AVAILABLE'::text AS available,
        'RESERVED'::text AS reserved,
        'ACTIVE'::text AS active,
        'DISABLED'::text AS disabled,
        'DELETED'::text AS deleted,

        'SCHEDULED'::text AS scheduled,
        'DELAYED'::text AS delayed,
        'CANCELLED'::text AS cancelled,
        'BOARDING'::text AS boarding,

        'Economy'::text AS economy,
        'Business'::text AS business,
        'Premium Economy'::text AS premium_economy,
        'First Class'::text AS first_class,

        'Credit Card'::text AS credit_card,
        'PayPal'::text AS paypal,
        'Bank Transfer'::text AS bank_transfer,

        'COMPLETED'::text AS completed,
        'PENDING'::text AS pending,
        'PROCESSING'::text AS processing
)

-- ADDRESSES
INSERT INTO address (id, street, city, state, country, postalcode)
VALUES ('AD-001', '123 Main St', 'New York', 'NY', 'USA', '10001')
ON CONFLICT (id) DO NOTHING;

INSERT INTO address (id, street, city, state, country, postalcode)
VALUES ('AD-002', '456 Elm St', 'Los Angeles', 'CA', 'USA', '90001')
ON CONFLICT (id) DO NOTHING;

INSERT INTO address (id, street, city, state, country, postalcode)
VALUES ('AD-003', '789 Oak Ave', 'Chicago', 'IL', 'USA', '60601')
ON CONFLICT (id) DO NOTHING;

INSERT INTO address (id, street, city, state, country, postalcode)
VALUES ('AD-004', '321 Pine Blvd', 'Dallas', 'TX', 'USA', '75201')
ON CONFLICT (id) DO NOTHING;

INSERT INTO address (id, street, city, state, country, postalcode)
VALUES ('AD-005', '654 Ocean Dr', 'Miami', 'FL', 'USA', '33101')
ON CONFLICT (id) DO NOTHING;


-- AIRPORTS
INSERT INTO airport (code, address_id)
VALUES ('JFK', 'AD-001')
ON CONFLICT (code) DO NOTHING;

INSERT INTO airport (code, address_id)
VALUES ('LAX', 'AD-002')
ON CONFLICT (code) DO NOTHING;

INSERT INTO airport (code, address_id)
VALUES ('ORD', 'AD-003')
ON CONFLICT (code) DO NOTHING;

INSERT INTO airport (code, address_id)
VALUES ('DFW', 'AD-004')
ON CONFLICT (code) DO NOTHING;

INSERT INTO airport (code, address_id)
VALUES ('MIA', 'AD-005')
ON CONFLICT (code) DO NOTHING;


-- AIRCRAFT
INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES ('737 Max', 'Boeing', 189)
ON CONFLICT (name) DO NOTHING;

INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES ('A320neo', 'Airbus', 180)
ON CONFLICT (name) DO NOTHING;

INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES ('787 Dreamliner', 'Boeing', 296)
ON CONFLICT (name) DO NOTHING;

INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES ('A350-900', 'Airbus', 325)
ON CONFLICT (name) DO NOTHING;

INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES ('Embraer E195-E2', 'Embraer', 146)
ON CONFLICT (name) DO NOTHING;


-- AIRLINES
INSERT INTO airline (id, code, name)
VALUES (1, 'DL', 'Delta Air Lines')
ON CONFLICT (id) DO NOTHING;

INSERT INTO airline (id, code, name)
VALUES (2, 'AA', 'American Airlines')
ON CONFLICT (id) DO NOTHING;

INSERT INTO airline (id, code, name)
VALUES (3, 'UA', 'United Airlines')
ON CONFLICT (id) DO NOTHING;

INSERT INTO airline (id, code, name)
VALUES (4, 'SW', 'Southwest Airlines')
ON CONFLICT (id) DO NOTHING;

INSERT INTO airline (id, code, name)
VALUES (5, 'JB', 'JetBlue Airways')
ON CONFLICT (id) DO NOTHING;


-- BOOKINGS
INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-8472', '2026-06-10', TRUE)
ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-9911', '2026-06-12', FALSE)
ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-1200', '2026-06-13', TRUE)
ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-1201', '2026-06-14', TRUE)
ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-1202', '2026-06-15', FALSE)
ON CONFLICT (booking_reference) DO NOTHING;


-- FLIGHTS
INSERT INTO flight (
    id, departure_date, arrival_date, distance,
    estimated_time_in_minutes, flight_status,
    aircraft_name, airline_id,
    origin_airport_code, destination_airport_code
)
VALUES
('FL-123', '2026-06-11 08:00:00', '2026-06-11 10:30:00', 850, 150, 'SCHEDULED', '737 Max', 1, 'JFK', 'LAX'),
('FL-456', '2026-06-12 14:15:00', '2026-06-12 18:45:00', 1600, 270, 'DELAYED', 'A320neo', 2, 'LAX', 'JFK'),
('FL-789', '2026-06-15 07:00:00', '2026-06-15 09:20:00', 1180, 140, 'SCHEDULED', '787 Dreamliner', 3, 'ORD', 'JFK'),
('FL-790', '2026-06-15 13:30:00', '2026-06-15 16:10:00', 1290, 160, 'BOARDING', 'A350-900', 4, 'DFW', 'MIA'),
('FL-791', '2026-06-16 18:45:00', '2026-06-16 21:15:00', 1375, 150, 'CANCELLED', 'Embraer E195-E2', 5, 'MIA', 'ORD')
ON CONFLICT (id) DO NOTHING;


-- TICKETS
INSERT INTO ticket (ticket_code, type, price, availability, flight_id)
VALUES
('TC-9001', c.economy, 150.00, c.available, 'FL-123'),
('TC-9002', c.business, 450.00, c.reserved, 'FL-456'),
('TC-9003', c.economy, 180.00, c.available, 'FL-789'),
('TC-9004', c.premium_economy, 320.00, c.available, 'FL-790'),
('TC-9005', c.business, 650.00, c.reserved, 'FL-790'),
('TC-9006', c.first_class, 1200.00, c.available, 'FL-791')
FROM const c
ON CONFLICT (ticket_code) DO NOTHING;


-- LUGGAGE
INSERT INTO luggage (id, weight, type, price)
VALUES
(1, 23.00, 'Checked Bag', 50.0),
(2, 8.50, 'Cabin Bag', 0.0),
(3, 15.00, 'Sports Equipment', 75.0),
(4, 30.00, 'Oversized Bag', 120.0),
(5, 5.00, 'Personal Item', 0.0)
ON CONFLICT (id) DO NOTHING;


-- USERS
INSERT INTO users (id, status, username, password, first_name, last_name)
VALUES
('US-001', c.active, 'johndoe', 'secret123', 'John', 'Doe'),
('US-002', c.disabled, 'janesmith', 'p@ssword', 'Jane', 'Smith'),
('US-003', c.active, 'mjohnson', 'password123', 'Michael', 'Johnson'),
('US-004', c.active, 'edavis', 'securepass', 'Emily', 'Davis'),
('US-005', c.deleted, 'crodriguez', 'deletedpass', 'Carlos', 'Rodriguez')
FROM const c
ON CONFLICT (id) DO NOTHING;


-- INVOICES
INSERT INTO invoice (id, cost, created_at, payment_method, status)
VALUES
(1, 120.50, '2026-06-12 10:15:00', c.completed, c.credit_card),
(2, 45.00, '2026-06-12 11:30:00', c.pending, c.paypal),
(3, 320.00, '2026-06-13 09:00:00', c.completed, c.credit_card),
(4, 650.00, '2026-06-14 15:45:00', c.processing, c.bank_transfer),
(5, 1200.00, '2026-06-15 12:20:00', c.pending, c.paypal)
FROM const c
ON CONFLICT (id) DO NOTHING;