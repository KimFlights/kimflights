package com.kimgroup.kimflights.payment.service;

import com.kimgroup.kimflights.payment.dto.InvoiceDTO;
import com.kimgroup.kimflights.payment.repository.InvoiceRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceDTO> findAll() {
        return invoiceRepository.findAll().stream()
            .map(invoice -> InvoiceDTO.builder()
                .id(invoice.getId())
                .createdAt(invoice.getCreatedAt())
                .cost(invoice.getCost())
                .paymentMethod(invoice.getPaymentMethod())
                .status(invoice.getStatus())
                .build())
            .toList();
    }
}
