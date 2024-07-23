package com.cinema.backend.modal.order;

import com.cinema.backend.modal.payment.Payment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderResponse {
    String maGd;
    Payment payment;
    Integer id;
}
