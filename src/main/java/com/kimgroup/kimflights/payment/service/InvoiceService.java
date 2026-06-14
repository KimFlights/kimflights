package com.kimgroup.kimflights.payment.service;

import com.kimgroup.kimflights.payment.dto.InvoiceDTO;
import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> findAll();
    InvoiceDTO create(String bookingId);
}
