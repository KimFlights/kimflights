package com.kimgroup.kimflights.booking.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BookingRepository extends JpaRepository<Booking, String> {
}
