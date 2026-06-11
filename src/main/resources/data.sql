INSERT INTO aircraft (name, manufacturer, seat_capacity) VALUES ('737 Max', 'Boeing', 189) ON CONFLICT (name) DO NOTHING;
INSERT INTO aircraft (name, manufacturer, seat_capacity) VALUES ('A320neo', 'Airbus', 180) ON CONFLICT (name) DO NOTHING;
INSERT INTO booking (booking_reference, reserved_date, booking_status) VALUES ('BK-8472', '2026-06-10', true) ON CONFLICT (booking_reference) DO NOTHING;
INSERT INTO booking (booking_reference, reserved_date, booking_status) VALUES ('BK-9911', '2026-06-12', false) ON CONFLICT (booking_reference) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes) VALUES ('FL-123', '2026-06-11 08:00:00', '2026-06-11 10:30:00', 850, 150) ON CONFLICT (id) DO NOTHING;
INSERT INTO flight (id, departure_date, arrival_date, distance, estimated_time_in_minutes) VALUES ('FL-456', '2026-06-12 14:15:00', '2026-06-12 18:45:00', 1600, 270) ON CONFLICT (id) DO NOTHING;
INSERT INTO ticket (ticket_code, type, price, availability, flight_id) VALUES ('TC-9001', 'Economy', 150.00, 'AVAILABLE', 'FL-123') ON CONFLICT (ticket_code) DO NOTHING;
INSERT INTO ticket (ticket_code, type, price, availability, flight_id) VALUES ('TC-9002', 'Business', 450.00, 'RESERVED', 'FL-123') ON CONFLICT (ticket_code) DO NOTHING;