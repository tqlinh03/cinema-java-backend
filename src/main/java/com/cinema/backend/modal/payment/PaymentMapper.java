package com.cinema.backend.modal.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .Status(false)
                .amount(paymentRequest.amount())
                .build();
    }

}
