package com.cinema.backend.modal.notification;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.order.Order;
import com.cinema.backend.modal.payment.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Notification extends BaseEntity {
    private NotificationType type;
    private LocalDateTime notificationDate;

//    private OrderConfirmation orderConfirmation;
//    private PaymentConfirmation paymentConfirmation;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
