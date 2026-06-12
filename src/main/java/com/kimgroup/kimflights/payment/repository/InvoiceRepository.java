package com.kimgroup.kimflights.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.payment.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
