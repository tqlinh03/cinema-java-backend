package com.cinema.backend.modal.ticket;

import java.math.BigDecimal;

public record TicketRequest(
        String seatNumber,
        String seatType,
        BigDecimal price,
        int showtimeId
) {

}
