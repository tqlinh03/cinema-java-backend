package com.cinema.backend.modal.ticket;

import com.cinema.backend.modal.showtimes.ShowtimesRepository;
import com.cinema.backend.modal.showtimes.entity.Showtimes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketMapper {
    public final ShowtimesRepository showtimesRepository;

    public Ticket toTicket(TicketRequest ticketRequest) {
        Showtimes showtime =  showtimesRepository.findById(ticketRequest.showtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
        return Ticket.builder()
                .seatNumber(ticketRequest.seatNumber())
                .price(ticketRequest.price())
                .seatType(ticketRequest.seatType())
                .showtime(showtime)
                .build();
    }

    public TicketResponse toTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .seatNumber(ticket.getSeatNumber())
                .totalPrice(ticket.getPrice())
                .build();
    }

}
