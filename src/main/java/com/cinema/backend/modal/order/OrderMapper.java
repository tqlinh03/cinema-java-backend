package com.cinema.backend.modal.order;

import com.cinema.backend.modal.payment.Payment;
import com.cinema.backend.modal.payment.PaymentRepository;
import com.cinema.backend.modal.payment.PaymentRequest;
import com.cinema.backend.modal.payment.PaymentService;
import com.cinema.backend.modal.rota.Rota;
import com.cinema.backend.modal.rota.RotaRepository;
import com.cinema.backend.modal.rota.RotaService;
import com.cinema.backend.modal.ticket.Ticket;
import com.cinema.backend.modal.ticket.TicketRepository;
import com.cinema.backend.modal.ticket.TicketRequest;
import com.cinema.backend.modal.ticket.TicketService;
import com.cinema.backend.modal.user.UserRepository;
import com.cinema.backend.modal.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMapper {
    public final UserRepository userRepository;
    public final PaymentService paymentService;
    public final RotaRepository rotaRepository;
    public final TicketService ticketService;
    public final RotaService rotaService;

    @Transactional
    public Order toOrderMapper(OrderRequest orderRequest) {
        List<TicketRequest> ticketOder = orderRequest.order();
        List<Ticket> tickets = new ArrayList<>();
        BigDecimal amount = BigDecimal.ZERO;

        for (TicketRequest ticket : ticketOder) {
            Ticket ticketData = this.ticketService.create(ticket);
            amount = amount.add(ticketData.getPrice()) ;
            tickets.add(ticketData);
        }

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        Rota rota = null;
        if (rotaService.currentRota(currentDate, currentTime) != null) {
            rota = this.rotaService.currentRota(currentDate, currentTime);
        }

        User user = userRepository.findById(orderRequest.userId()).orElse(null);

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .Status(false)
                .amount(amount)
                .build();
        Payment payment = paymentService.createPayment(paymentRequest);
        return Order.builder()
                .user(user)
                .tickets(tickets)
                .rota(rota)
                .payment(payment)
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .maGd(order.getMaGd())
                .payment(order.getPayment())
                .build();
    }

}
