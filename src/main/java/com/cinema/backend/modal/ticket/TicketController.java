package com.cinema.backend.modal.ticket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    public final TicketService ticketService;

//    @PostMapping
//    public ResponseEntity<Integer> createTicket(
//            @RequestBody @Valid TicketRequest ticket
//    ) {
//        return ResponseEntity.ok(ticketService.create(ticket));
//    }
}
