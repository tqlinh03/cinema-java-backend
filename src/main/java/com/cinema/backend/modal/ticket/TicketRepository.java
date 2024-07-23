package com.cinema.backend.modal.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketRepository extends JpaRepository<Ticket, Integer>,
        JpaSpecificationExecutor<Ticket> {

}
