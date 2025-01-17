package com.cinema.backend.modal.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRepository extends JpaRepository<Payment, Integer>,
        JpaSpecificationExecutor<Payment>
{
}
