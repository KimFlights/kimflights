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

INSERT INTO airport (code, name, city, country, region)
VALUES
    ('JFK', 'John F. Kennedy International Airport', 'New York', 'USA', 'New York'),
    ('LAX', 'Los Angeles International Airport', 'Los Angeles', 'USA', 'California'),
    ('ORD', 'O''Hare International Airport', 'Chicago', 'USA', 'Illinois'),
    ('DFW', 'Dallas/Fort Worth International Airport', 'Dallas-Fort Worth', 'USA', 'Texas')
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
-- AIRCRAFT SEATS
-- ==========================================

INSERT INTO aircraft_seat (id, cabin, seat_number, aircraft_name)
VALUES
    ('seat-b737-1a', 'First', '1A', 'Boeing 737-800'),
    ('seat-b737-1b', 'First', '1B', 'Boeing 737-800'),
    ('seat-b737-12a', 'Economy', '12A', 'Boeing 737-800'),
    ('seat-a320-1a', 'Business', '1A', 'Airbus A320'),
    ('seat-a320-1b', 'Business', '1B', 'Airbus A320'),
    ('seat-a320-10a', 'Economy', '10A', 'Airbus A320'),
    ('seat-b787-1a', 'First', '1A', 'Boeing 787-9'),
    ('seat-b787-2a', 'Business', '2A', 'Boeing 787-9'),
    ('seat-b787-30a', 'Economy', '30A', 'Boeing 787-9')
ON CONFLICT (id) DO NOTHING;

