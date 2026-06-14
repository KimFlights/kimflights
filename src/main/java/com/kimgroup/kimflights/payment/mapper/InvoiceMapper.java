package com.kimgroup.kimflights.payment.mapper;

import com.kimgroup.kimflights.payment.dto.InvoiceDTO;
import com.kimgroup.kimflights.payment.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    public InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .bookingId(invoice.getBookingId())
                .createdAt(invoice.getCreatedAt())
                .cost(invoice.getCost())
                .paymentMethod(invoice.getPaymentMethod())
                .status(invoice.getStatus())
                .build();
    }
}
