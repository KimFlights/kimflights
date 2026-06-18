package com.kimgroup.kimflights.notification.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotificationServiceImplTest {

    private final NotificationServiceImpl notificationService = new NotificationServiceImpl();

    @Test
    void shouldLogReceiptWithoutThrowing() {
        assertDoesNotThrow(() -> notificationService.sendReceipt(1, "SUCCESS"));
    }
}
