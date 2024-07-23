package com.cinema.backend.modal.ticket;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.order.Order;
import com.cinema.backend.modal.showtimes.entity.Showtimes;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Ticket extends BaseEntity {
    String seatNumber;
    String seatType;
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "showtime_id",  referencedColumnName = "id")
    Showtimes showtime;

    @ManyToOne
    @JoinColumn(name = "order_id",  referencedColumnName = "id")
    Order order;

}
