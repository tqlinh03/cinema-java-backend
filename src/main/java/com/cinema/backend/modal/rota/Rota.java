package com.cinema.backend.modal.rota;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.order.Order;
import com.cinema.backend.modal.shift.Shift;
import com.cinema.backend.modal.staff.Staff;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Rota extends BaseEntity {
    private LocalDate date;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "rota_staff",
            joinColumns = @JoinColumn(name = "rota_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    @JsonManagedReference
    private List<Staff> staffs;

    @ManyToOne
    @JoinColumn(name = "shift_id", referencedColumnName = "id")
    @JsonManagedReference
    private Shift shift;

    @OneToMany(mappedBy = "rota")
    private List<Order> orders;
}
