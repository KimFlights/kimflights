package com.kimgroup.kimflights.payment.service.impl;

import com.kimgroup.kimflights.payment.service.InvoiceService;
import com.kimgroup.kimflights.payment.dto.InvoiceDTO;
import com.kimgroup.kimflights.payment.repository.InvoiceRepository;
import com.kimgroup.kimflights.payment.model.Invoice;
import com.kimgroup.kimflights.payment.model.InvoiceStatus;
import com.kimgroup.kimflights.booking.BookingService;
import com.kimgroup.kimflights.booking.dto.BookingResponse;

import org.springframework.stereotype.Service;

import com.kimgroup.kimflights.payment.mapper.InvoiceMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BookingService bookingService;
    private final InvoiceMapper invoiceMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, BookingService bookingService, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.bookingService = bookingService;
        this.invoiceMapper = invoiceMapper;
    }

    public List<InvoiceDTO> findAll() {
        return invoiceRepository.findAll().stream()
            .map(invoiceMapper::toDTO)
            .toList();
    }

    public InvoiceDTO create(String bookingId) {
        BookingResponse booking = bookingService.findById(bookingId);

        BigDecimal totalPrice = booking.totalPrice();

        Invoice invoice = new Invoice();
        invoice.setBookingId(bookingId);
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setCost(totalPrice);
        invoice.setStatus(InvoiceStatus.PENDING);

        Invoice saved = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(saved);
    }

}
