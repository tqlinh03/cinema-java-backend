package com.cinema.backend.modal.staff;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.rota.Rota;
import com.cinema.backend.modal.store.Store;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Staff extends BaseEntity {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private String address;
    private String img;
    private String gender;
    private String position;
    private Float hourly_rate;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    @JsonBackReference
    private Store store;

    @ManyToMany(mappedBy = "staffs")
    @JsonBackReference
    private List<Rota> rotas;

}
