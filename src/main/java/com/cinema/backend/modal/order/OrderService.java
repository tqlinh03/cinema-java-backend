package com.cinema.backend.modal.order;

import com.cinema.backend.modal.rota.Rota;
import com.cinema.backend.modal.rota.RotaResponse;
import com.cinema.backend.modal.rota.RotaService;
import com.cinema.backend.modal.ticket.Ticket;
import com.cinema.backend.modal.ticket.TicketRequest;
import com.cinema.backend.modal.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final OrderRepository orderRepository;
    public final OrderMapper orderMapper;

    public OrderResponse create(OrderRequest orderRequest) {
        Order order = orderMapper.toOrderMapper(orderRequest);
        order.setMaGd("MGD_ODER"+orderRepository.save(order).getId());
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }
}