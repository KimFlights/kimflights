package com.kimgroup.kimflights.notification.internal;

import com.kimgroup.kimflights.notification.NotificationService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    public void sendReceipt(Integer invoiceId, String transactionStatus) {
        log.info("MOCK NOTIFICATION: Sending receipt email to customer for invoice {}. Payment status: {}", invoiceId, transactionStatus);
    }
}
