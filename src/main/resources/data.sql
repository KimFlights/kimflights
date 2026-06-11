INSERT INTO aircraft (name, manufacturer, seat_capacity) VALUES ('737 Max', 'Boeing', 189) ON CONFLICT (name) DO NOTHING;
INSERT INTO aircraft (name, manufacturer, seat_capacity) VALUES ('A320neo', 'Airbus', 180) ON CONFLICT (name) DO NOTHING;
INSERT INTO booking (booking_reference, reserved_date, booking_status) VALUES ('BK-8472', '2026-06-10', true) ON CONFLICT (booking_reference) DO NOTHING;
INSERT INTO booking (booking_reference, reserved_date, booking_status) VALUES ('BK-9911', '2026-06-12', false) ON CONFLICT (booking_reference) DO NOTHING;