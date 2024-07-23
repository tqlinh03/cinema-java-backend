package com.cinema.backend.modal.order;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.payment.Payment;
import com.cinema.backend.modal.rota.Rota;
import com.cinema.backend.modal.ticket.Ticket;
import com.cinema.backend.modal.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_order")
@RequestMapping("/orders")
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity {
    private String maGd;
    @ManyToOne
    @JoinColumn(name = "rota_id", referencedColumnName = "id")
    private Rota rota;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<Ticket> tickets;
}
