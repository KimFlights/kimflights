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

INSERT INTO airport (code, name, address_id)
VALUES
    ('JFK', 'John F. Kennedy International Airport', 'ADDR-JFK'),
    ('LAX', 'Los Angeles International Airport', 'ADDR-LAX'),
    ('ORD', 'O''Hare International Airport', 'ADDR-ORD'),
    ('DFW', 'Dallas/Fort Worth International Airport', 'ADDR-DFW')
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
