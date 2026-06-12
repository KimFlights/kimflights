INSERT INTO address (id, street, city, state, country, postalcode) VALUES ('AD-001', '123 Main St', 'New York', 'NY', 'USA', '10001') ON CONFLICT (id) DO NOTHING;
INSERT INTO address (id, street, city, state, country, postalcode) VALUES ('AD-002', '456 Elm St', 'Los Angeles', 'CA', 'USA', '90001') ON CONFLICT (id) DO NOTHING;

INSERT INTO airport (code, address_id) VALUES ('JFK', 'AD-001') ON CONFLICT (code) DO NOTHING;
INSERT INTO airport (code, address_id) VALUES ('LAX', 'AD-002') ON CONFLICT (code) DO NOTHING;

INSERT INTO aircraft (name, manufacturer, seat_capacity) VALUES ('737 Max', 'Boeing', 189) ON CONFLICT (name) DO NOTHING;
INSERT INTO aircraft (name, manufacturer, seat_capacity) VALUES ('A320neo', 'Airbus', 180) ON CONFLICT (name) DO NOTHING;

INSERT INTO airline (id, code, name) VALUES (1, 'DL', 'Delta Air Lines') ON CONFLICT (id) DO NOTHING;
INSERT INTO airline (id, code, name) VALUES (2, 'AA', 'American Airlines') ON CONFLICT (id) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status) VALUES ('BK-8472', '2026-06-10', true) ON CONFLICT (booking_reference) DO NOTHING;
INSERT INTO booking (booking_reference, reserved_date, booking_status) VALUES ('BK-9911', '2026-06-12', false) ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code) VALUES ('FL-123', '2026-06-11 08:00:00', '2026-06-11 10:30:00', 850, 150, 'SCHEDULED', '737 Max', 1, 'JFK', 'LAX') ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code) VALUES ('FL-456', '2026-06-12 14:15:00', '2026-06-12 18:45:00', 1600, 270, 'DELAYED', 'A320neo', 2, 'LAX', 'JFK') ON CONFLICT (id) DO NOTHING;

INSERT INTO ticket (ticket_code, type, price, availability, flight_id) VALUES ('TC-9001', 'Economy', 150.00, 'AVAILABLE', 'FL-123') ON CONFLICT (ticket_code) DO NOTHING;
INSERT INTO ticket (ticket_code, type, price, availability, flight_id) VALUES ('TC-9002', 'Business', 450.00, 'RESERVED', 'FL-456') ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO luggage (id, weight, type, price) VALUES (1, 23.00, 'Checked Bag', 50.0) ON CONFLICT (id) DO NOTHING;
INSERT INTO luggage (id, weight, type, price) VALUES (2, 8.50, 'Cabin Bag', 0.0) ON CONFLICT (id) DO NOTHING;

INSERT INTO passenger (id, name, passport_number) VALUES ('PA-001', 'John Doe', 'US123456789') ON CONFLICT (id) DO NOTHING;
INSERT INTO passenger (id, name, passport_number) VALUES ('PA-002', 'Jane Smith', 'UK987654321') ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, status, username, password, first_name, last_name) VALUES ('US-001', 'ACTIVE', 'johndoe', 'secret123', 'John', 'Doe') ON CONFLICT (id) DO NOTHING;
INSERT INTO users (id, status, username, password, first_name, last_name) VALUES ('US-002', 'DISABLED', 'janesmith', 'p@ssword', 'Jane', 'Smith') ON CONFLICT (id) DO NOTHING;

INSERT INTO invoice (id, cost, created_at, payment_method, status) VALUES (1, 120.50, '2026-06-12 10:15:00', 'Credit Card', 'COMPLETED') ON CONFLICT (id) DO NOTHING;
INSERT INTO invoice (id, cost, created_at, payment_method, status) VALUES (2, 45.00, '2026-06-12 11:30:00', 'PayPal', 'PENDING') ON CONFLICT (id) DO NOTHING;

-- ADDRESSES
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
VALUES ('BK-1200', '2026-06-13', true)
ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-1201', '2026-06-14', true)
ON CONFLICT (booking_reference) DO NOTHING;

INSERT INTO booking (booking_reference, reserved_date, booking_status)
VALUES ('BK-1202', '2026-06-15', false)
ON CONFLICT (booking_reference) DO NOTHING;


-- FLIGHTS
INSERT INTO flight (
    id,
    departure_date,
    arrival_date,
    distance,
    estimated_time_in_minutes,
    flight_status,
    aircraft_name,
    airline_id,
    origin_airport_code,
    destination_airport_code
)
VALUES (
    'FL-789',
    '2026-06-15 07:00:00',
    '2026-06-15 09:20:00',
    1180,
    140,
    'SCHEDULED',
    '787 Dreamliner',
    3,
    'ORD',
    'JFK'
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO flight (
    id,
    departure_date,
    arrival_date,
    distance,
    estimated_time_in_minutes,
    flight_status,
    aircraft_name,
    airline_id,
    origin_airport_code,
    destination_airport_code
)
VALUES (
    'FL-790',
    '2026-06-15 13:30:00',
    '2026-06-15 16:10:00',
    1290,
    160,
    'BOARDING',
    'A350-900',
    4,
    'DFW',
    'MIA'
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO flight (
    id,
    departure_date,
    arrival_date,
    distance,
    estimated_time_in_minutes,
    flight_status,
    aircraft_name,
    airline_id,
    origin_airport_code,
    destination_airport_code
)
VALUES (
    'FL-791',
    '2026-06-16 18:45:00',
    '2026-06-16 21:15:00',
    1375,
    150,
    'CANCELLED',
    'Embraer E195-E2',
    5,
    'MIA',
    'ORD'
)
ON CONFLICT (id) DO NOTHING;


-- TICKETS
INSERT INTO ticket (ticket_code, type, price, availability, flight_id)
VALUES ('TC-9003', 'Economy', 180.00, 'AVAILABLE', 'FL-789')
ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO ticket (ticket_code, type, price, availability, flight_id)
VALUES ('TC-9004', 'Premium Economy', 320.00, 'AVAILABLE', 'FL-790')
ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO ticket (ticket_code, type, price, availability, flight_id)
VALUES ('TC-9005', 'Business', 650.00, 'RESERVED', 'FL-790')
ON CONFLICT (ticket_code) DO NOTHING;

INSERT INTO ticket (ticket_code, type, price, availability, flight_id)
VALUES ('TC-9006', 'First Class', 1200.00, 'AVAILABLE', 'FL-791')
ON CONFLICT (ticket_code) DO NOTHING;


-- LUGGAGE
INSERT INTO luggage (id, weight, type, price)
VALUES (3, 15.00, 'Sports Equipment', 75.00)
ON CONFLICT (id) DO NOTHING;

INSERT INTO luggage (id, weight, type, price)
VALUES (4, 30.00, 'Oversized Bag', 120.00)
ON CONFLICT (id) DO NOTHING;

INSERT INTO luggage (id, weight, type, price)
VALUES (5, 5.00, 'Personal Item', 0.00)
ON CONFLICT (id) DO NOTHING;


-- PASSENGERS
INSERT INTO passenger (id, name, passport_number)
VALUES ('PA-003', 'Michael Johnson', 'US555888111')
ON CONFLICT (id) DO NOTHING;

INSERT INTO passenger (id, name, passport_number)
VALUES ('PA-004', 'Emily Davis', 'CA777444222')
ON CONFLICT (id) DO NOTHING;

INSERT INTO passenger (id, name, passport_number)
VALUES ('PA-005', 'Carlos Rodriguez', 'MX999333555')
ON CONFLICT (id) DO NOTHING;


-- USERS
INSERT INTO users (
    id,
    status,
    username,
    password,
    first_name,
    last_name
)
VALUES (
    'US-003',
    'ACTIVE',
    'mjohnson',
    'password123',
    'Michael',
    'Johnson'
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (
    id,
    status,
    username,
    password,
    first_name,
    last_name
)
VALUES (
    'US-004',
    'ACTIVE',
    'edavis',
    'securepass',
    'Emily',
    'Davis'
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (
    id,
    status,
    username,
    password,
    first_name,
    last_name
)
VALUES (
    'US-005',
    'DELETED',
    'crodriguez',
    'deletedpass',
    'Carlos',
    'Rodriguez'
)
ON CONFLICT (id) DO NOTHING;


-- INVOICES
INSERT INTO invoice (
    id,
    cost,
    created_at,
    payment_method,
    status
)
VALUES (
    3,
    320.00,
    '2026-06-13 09:00:00',
    'Credit Card',
    'COMPLETED'
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO invoice (
    id,
    cost,
    created_at,
    payment_method,
    status
)
VALUES (
    4,
    650.00,
    '2026-06-14 15:45:00',
    'Bank Transfer',
    'PROCESSING'
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO invoice (
    id,
    cost,
    created_at,
    payment_method,
    status
)
VALUES (
    5,
    1200.00,
    '2026-06-15 12:20:00',
    'PayPal',
    'PENDING'
)
ON CONFLICT (id) DO NOTHING;