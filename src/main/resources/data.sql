-- ==============================================================
-- Airport seed data — 17 airports from frontend mock
-- Safe to run on every startup: ON CONFLICT (code) DO NOTHING
-- Columns: code, name, region, city, country, state
-- (city, country, state are the embedded AirportLocation columns)
-- ==============================================================

INSERT INTO airport (code, name, region, city, country, state)
VALUES
  ('LAX', 'Los Angeles Intl',    'California',       'Los Angeles',   'USA',       'California'),
  ('SFO', 'San Francisco Intl',  'California',       'San Francisco', 'USA',       'California'),
  ('SAN', 'San Diego Intl',      'California',       'San Diego',     'USA',       'California'),
  ('JFK', 'John F. Kennedy Intl','New York',         'New York',      'USA',       'New York'),
  ('EWR', 'Newark Liberty Intl', 'New York',         'Newark',        'USA',       'New York'),
  ('ORD', 'O''Hare Intl',        'Illinois',         'Chicago',       'USA',       'Illinois'),
  ('MIA', 'Miami Intl',          'Florida',          'Miami',         'USA',       'Florida'),
  ('SEA', 'Seattle-Tacoma Intl', 'Washington',       'Seattle',       'USA',       'Washington'),
  ('BOS', 'Logan Intl',          'Massachusetts',    'Boston',        'USA',       'Massachusetts'),
  ('LHR', 'Heathrow',            'England',          'London',        'UK',        'England'),
  ('CDG', 'Charles de Gaulle',   'Île-de-France',    'Paris',         'France',    'Île-de-France'),
  ('FCO', 'Fiumicino',           'Lazio',            'Rome',          'Italy',     'Lazio'),
  ('BCN', 'El Prat',             'Catalonia',        'Barcelona',     'Spain',     'Catalonia'),
  ('HND', 'Haneda',              'Kanto',            'Tokyo',         'Japan',     'Kanto'),
  ('SIN', 'Changi',              'Singapore',        'Singapore',     'Singapore', 'Singapore'),
  ('DXB', 'Dubai Intl',          'Dubai',            'Dubai',         'UAE',       'Dubai'),
  ('SYD', 'Kingsford Smith',     'New South Wales',  'Sydney',        'Australia', 'New South Wales')
ON CONFLICT (code) DO NOTHING;

-- ==============================================================
-- Airline seed data
-- ==============================================================

INSERT INTO airline (code, name)
SELECT 'KA', 'KimAir'         WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'KA');
INSERT INTO airline (code, name)
SELECT 'VA', 'VegaAir'        WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'VA');
INSERT INTO airline (code, name)
SELECT 'NA', 'Nova Air'        WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'NA');
INSERT INTO airline (code, name)
SELECT 'PL', 'Polaris Airways' WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'PL');
INSERT INTO airline (code, name)
SELECT 'HA', 'Helio Airlines'  WHERE NOT EXISTS (SELECT 1 FROM airline WHERE code = 'HA');

-- ==============================================================
-- Aircraft seed data (PK = name)
-- ==============================================================

INSERT INTO aircraft (name, manufacturer, seat_capacity)
VALUES
  ('Boeing 787-9',   'Boeing',  296),
  ('Boeing 777-300', 'Boeing',  396),
  ('Airbus A350-900','Airbus',  315),
  ('Airbus A321neo', 'Airbus',  194),
  ('Boeing 737 MAX', 'Boeing',  178),
  ('Airbus A380-800','Airbus',  555)
ON CONFLICT (name) DO NOTHING;

-- ==============================================================
-- Flight seed data — popular routes, next 60 days, ~3 per day
-- Departure times spread: 07:00, 13:00, 20:00
-- Duration and distance are realistic approximations.
-- airline_id is resolved via a sub-select so this is resilient
-- to IDENTITY sequence gaps across restarts.
-- ==============================================================

-- Helper: resolves airline id by name
-- JFK → CDG  (7h 30m = 450 min, ~5 840 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-JFK-CDG-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '7 hours',  d + INTERVAL '14 hours 30 minutes', 5840, 450, 'SCHEDULED', 'Boeing 787-9',   (SELECT id FROM airline WHERE code='KA'), 'JFK', 'CDG' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-JFK-CDG-' || TO_CHAR(d, 'YYYYMMDD') || '-2', d + INTERVAL '13 hours', d + INTERVAL '20 hours 30 minutes', 5840, 450, 'SCHEDULED', 'Airbus A350-900', (SELECT id FROM airline WHERE code='VA'), 'JFK', 'CDG' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-JFK-CDG-' || TO_CHAR(d, 'YYYYMMDD') || '-3', d + INTERVAL '20 hours', d + INTERVAL '27 hours 30 minutes', 5840, 450, 'SCHEDULED', 'Boeing 777-300',  (SELECT id FROM airline WHERE code='PL'), 'JFK', 'CDG' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- JFK → LHR  (7h = 420 min, ~5 570 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-JFK-LHR-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '8 hours',  d + INTERVAL '15 hours', 5570, 420, 'SCHEDULED', 'Boeing 787-9',   (SELECT id FROM airline WHERE code='KA'), 'JFK', 'LHR' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-JFK-LHR-' || TO_CHAR(d, 'YYYYMMDD') || '-2', d + INTERVAL '22 hours', d + INTERVAL '29 hours', 5570, 420, 'SCHEDULED', 'Airbus A380-800', (SELECT id FROM airline WHERE code='HA'), 'JFK', 'LHR' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- LAX → HND  (11h = 660 min, ~8 750 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-LAX-HND-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '10 hours', d + INTERVAL '21 hours', 8750, 660, 'SCHEDULED', 'Boeing 787-9',    (SELECT id FROM airline WHERE code='NA'), 'LAX', 'HND' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-LAX-HND-' || TO_CHAR(d, 'YYYYMMDD') || '-2', d + INTERVAL '22 hours', d + INTERVAL '33 hours', 8750, 660, 'SCHEDULED', 'Airbus A350-900', (SELECT id FROM airline WHERE code='PL'), 'LAX', 'HND' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- LAX → LHR  (10h 30m = 630 min, ~8 760 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-LAX-LHR-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '9 hours',  d + INTERVAL '19 hours 30 minutes', 8760, 630, 'SCHEDULED', 'Boeing 777-300',  (SELECT id FROM airline WHERE code='KA'), 'LAX', 'LHR' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-LAX-LHR-' || TO_CHAR(d, 'YYYYMMDD') || '-2', d + INTERVAL '19 hours', d + INTERVAL '29 hours 30 minutes', 8760, 630, 'SCHEDULED', 'Airbus A380-800', (SELECT id FROM airline WHERE code='VA'), 'LAX', 'LHR' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- MIA → CDG  (9h 30m = 570 min, ~7 570 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-MIA-CDG-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '21 hours', d + INTERVAL '30 hours 30 minutes', 7570, 570, 'SCHEDULED', 'Boeing 787-9',   (SELECT id FROM airline WHERE code='HA'), 'MIA', 'CDG' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- LAX → SYD  (14h 30m = 870 min, ~12 080 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-LAX-SYD-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '22 hours', d + INTERVAL '36 hours 30 minutes', 12080, 870, 'SCHEDULED', 'Airbus A380-800', (SELECT id FROM airline WHERE code='KA'), 'LAX', 'SYD' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- ORD → LHR  (8h = 480 min, ~6 350 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-ORD-LHR-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '17 hours', d + INTERVAL '25 hours', 6350, 480, 'SCHEDULED', 'Boeing 787-9',   (SELECT id FROM airline WHERE code='VA'), 'ORD', 'LHR' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- BOS → CDG  (7h = 420 min, ~5 560 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-BOS-CDG-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '18 hours', d + INTERVAL '25 hours', 5560, 420, 'SCHEDULED', 'Airbus A350-900', (SELECT id FROM airline WHERE code='NA'), 'BOS', 'CDG' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- SFO → HND  (10h 30m = 630 min, ~8 280 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-SFO-HND-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '11 hours', d + INTERVAL '21 hours 30 minutes', 8280, 630, 'SCHEDULED', 'Boeing 787-9',    (SELECT id FROM airline WHERE code='PL'), 'SFO', 'HND' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;

-- JFK → SIN  (18h 40m = 1120 min, ~15 350 km)
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes, flight_status, aircraft_name, airline_id, origin_airport_code, destination_airport_code)
SELECT 'FL-JFK-SIN-' || TO_CHAR(d, 'YYYYMMDD') || '-1', d + INTERVAL '10 hours', d + INTERVAL '28 hours 40 minutes', 15350, 1120, 'SCHEDULED', 'Airbus A350-900', (SELECT id FROM airline WHERE code='KA'), 'JFK', 'SIN' FROM generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', '1 day') AS d
ON CONFLICT (id) DO NOTHING;
