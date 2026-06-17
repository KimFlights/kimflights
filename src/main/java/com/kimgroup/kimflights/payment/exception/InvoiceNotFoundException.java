package com.kimgroup.kimflights.payment.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(Integer id) {
        super("Invoice not found: " + id);
    }
}
