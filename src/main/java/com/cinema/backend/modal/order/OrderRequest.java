package com.cinema.backend.modal.order;

import com.cinema.backend.modal.ticket.TicketRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotNull
       List<TicketRequest> order,
        Integer userId

) {
}
