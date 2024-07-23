package com.cinema.backend.modal.ticket;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class TicketResponse {
    Integer id;
    String seatNumber;
    BigDecimal totalPrice;
}
