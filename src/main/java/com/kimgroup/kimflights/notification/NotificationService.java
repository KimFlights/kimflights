package com.kimgroup.kimflights.notification;

public interface NotificationService {
    void sendReceipt(Integer invoiceId, String transactionStatus);
}
