package com.cinema.backend.modal.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        BigDecimal amount,
        Boolean Status
) {
}
