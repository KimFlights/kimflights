package com.kimgroup.kimflights.payment.repository;

import com.kimgroup.kimflights.payment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
