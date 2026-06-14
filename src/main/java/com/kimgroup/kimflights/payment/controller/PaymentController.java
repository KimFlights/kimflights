package com.kimgroup.kimflights.payment.controller;

import com.kimgroup.kimflights.payment.dto.PaymentRequestDTO;
import com.kimgroup.kimflights.payment.dto.TransactionDTO;
import com.kimgroup.kimflights.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/brand/{cardNumber}")
    public String verifyCardBrand(@PathVariable String cardNumber) {
        return paymentService.getCardBrand(cardNumber);
    }

    @PostMapping("/pay")
    public Long simulatePayment(@Valid @RequestBody PaymentRequestDTO request) {
        TransactionDTO transaction = paymentService.processPayment(request);
        return transaction.id();
    }

    @GetMapping("/transaction/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return paymentService.getTransaction(id);
    }
}
