package com.kimgroup.kimflights.booking.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class BookingReferenceGenerator {

    private static final String CHARS =
            "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private static final int LENGTH = 6;

    private final SecureRandom random = new SecureRandom();

    public String generate() {

        StringBuilder builder = new StringBuilder("KF-");

        for (int i = 0; i < LENGTH; i++) {
            builder.append(
                    CHARS.charAt(
                            random.nextInt(CHARS.length())
                    )
            );
        }

        return builder.toString();
    }
}