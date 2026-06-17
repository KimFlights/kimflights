-- ==========================================
-- AIRCRAFT
-- ==========================================

INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES
('Boeing 737-800', 'Boeing', 189),
('Airbus A320', 'Airbus', 180),
('Boeing 787-9', 'Boeing', 296)
ON CONFLICT (name) DO NOTHING;

-- ==========================================
-- AIRLINES
-- ==========================================

INSERT INTO airline (id, code, name)
VALUES
(1, 'AA', 'American Airlines'),
(2, 'DL', 'Delta Airlines'),
(3, 'UA', 'United Airlines')
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- AIRPORTS
-- ==========================================

INSERT INTO airport (code, address_id)
VALUES
('JFK', 'ADDR-JFK'),
('LAX', 'ADDR-LAX'),
('ORD', 'ADDR-ORD'),
('DFW', 'ADDR-DFW')
ON CONFLICT (code) DO NOTHING;

-- ==========================================
-- FLIGHTS
-- ==========================================

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
VALUES
(
    'AA101',
    '2026-07-01 08:00:00',
    '2026-07-01 11:30:00',
    2475,
    210,
    'SCHEDULED',
    'Boeing 737-800',
    1,
    'JFK',
    'LAX'
),
(
    'DL202',
    '2026-07-02 09:15:00',
    '2026-07-02 12:00:00',
    1180,
    165,
    'BOARDING',
    'Airbus A320',
    2,
    'ORD',
    'DFW'
),
(
    'UA303',
    '2026-07-03 13:00:00',
    '2026-07-03 18:30:00',
    3983,
    330,
    'SCHEDULED',
    'Boeing 787-9',
    3,
    'LAX',
    'JFK'
)
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- BOOKINGS
-- ==========================================

INSERT INTO booking (
    booking_reference,
    reserved_date,
    status
)
VALUES
('BK-10001', '2026-06-15', 'CONFIRMED'),
('BK-10002', '2026-06-16', 'PENDING_PAYMENT'),
('BK-10003', '2026-06-17', 'CHECKED_IN')
ON CONFLICT (booking_reference) DO NOTHING;

-- ==========================================
-- PASSENGERS
-- ==========================================

INSERT INTO passenger (
    id,
    name,
    passport_number,
    booking_reference,
    street,
    city,
    state,
    country,
    postal_code
)
VALUES
(
    '11111111-1111-1111-1111-111111111111',
    'John Smith',
    'P1234567',
    'BK-10001',
    '123 Main Street',
    'New York',
    'NY',
    'USA',
    '10001'
),
(
    '22222222-2222-2222-2222-222222222222',
    'Sarah Johnson',
    'P2345678',
    'BK-10002',
    '456 Oak Avenue',
    'Chicago',
    'IL',
    'USA',
    '60601'
),
(
    '33333333-3333-3333-3333-333333333333',
    'Michael Brown',
    'P3456789',
    'BK-10003',
    '789 Sunset Blvd',
    'Los Angeles',
    'CA',
    'USA',
    '90001'
)
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- TICKETS
-- ==========================================

INSERT INTO ticket (
    ticket_code,
    availability,
    flight_id,
    price,
    type,
    passenger_id
)
VALUES
(
    'TKT-001',
    'RESERVED',
    'AA101',
    450.00,
    'ECONOMY',
    '11111111-1111-1111-1111-111111111111'
),
(
    'TKT-002',
    'AVAILABLE',
    'DL202',
    320.00,
    'ECONOMY',
    '22222222-2222-2222-2222-222222222222'
),
(
    'TKT-003',
    'RESERVED',
    'UA303',
    850.00,
    'BUSINESS',
    '33333333-3333-3333-3333-333333333333'
)
ON CONFLICT (ticket_code) DO NOTHING;

-- ==========================================
-- LUGGAGE
-- ==========================================

INSERT INTO luggage (
    id,
    weight,
    type,
    price,
    passenger_id
)
VALUES
(
    1,
    23.50,
    'CHECKED',
    50.00,
    '11111111-1111-1111-1111-111111111111'
),
(
    2,
    10.00,
    'CARRY_ON',
    0.00,
    '22222222-2222-2222-2222-222222222222'
),
(
    3,
    28.00,
    'CHECKED',
    75.00,
    '33333333-3333-3333-3333-333333333333'
)
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- INVOICES
-- ==========================================

INSERT INTO invoice (
    id,
    booking_id,
    cost,
    created_at,
    payment_method,
    status
)
VALUES
(
    1,
    'BK-10001',
    500.00,
    '2026-06-15 10:00:00',
    'VISA',
    'COMPLETED'
),
(
    2,
    'BK-10002',
    320.00,
    '2026-06-16 11:15:00',
    'MASTERCARD',
    'PENDING'
)
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- TRANSACTIONS
-- ==========================================

INSERT INTO transaction (
    id,
    card_number,
    brand,
    amount,
    status,
    failure_reason,
    created_at
)
VALUES
(
    1,
    '4111111111111111',
    'VISA',
    500.00,
    'SUCCESS',
    NULL,
    '2026-06-15 10:01:00'
),
(
    2,
    '5555555555554444',
    'MASTERCARD',
    320.00,
    'FAILED',
    'INSUFFICIENT_FUNDS',
    '2026-06-16 11:20:00'
)
ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- USERS
-- Password = password
-- BCrypt hash:
-- $2a$10$DowJonesIndexExampleHashReplaceMe
-- ==========================================

INSERT INTO users (
    id,
    username,
    password,
    first_name,
    last_name,
    status,
    role
)
VALUES
(
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    'admin',
    '$2a$10$7EqJtq98hPqEX7fNZaFWoOHiYtQjF9zQ0lN5xRcbk6VwBEm90LkK2',
    'System',
    'Admin',
    'ACTIVE',
    'ROLE_ADMIN'
),
(
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    'user',
    '$2a$10$7EqJtq98hPqEX7fNZaFWoOHiYtQjF9zQ0lN5xRcbk6VwBEm90LkK2',
    'Regular',
    'User',
    'ACTIVE',
    'ROLE_USER'
)
ON CONFLICT (id) DO NOTHING;