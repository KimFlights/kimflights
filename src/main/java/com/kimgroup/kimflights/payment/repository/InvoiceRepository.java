package com.kimgroup.kimflights.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kimgroup.kimflights.payment.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
