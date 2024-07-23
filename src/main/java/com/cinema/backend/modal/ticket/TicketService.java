package com.cinema.backend.modal.ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    public final TicketMapper ticketMapper;
    public  final TicketRepository ticketRepository;

    public Ticket create(TicketRequest ticketRequest) {
        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        return ticketRepository.save(ticket);
    }
}
