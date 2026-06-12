package com.kimgroup.kimflights.payment.controller;

import com.kimgroup.kimflights.payment.dto.InvoiceDTO;
import com.kimgroup.kimflights.payment.service.InvoiceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<InvoiceDTO> findAll() {
        return invoiceService.findAll();
    }
}
