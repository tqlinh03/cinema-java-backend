package com.cinema.backend.modal.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Integer>,
        JpaSpecificationExecutor<Order> {
}
