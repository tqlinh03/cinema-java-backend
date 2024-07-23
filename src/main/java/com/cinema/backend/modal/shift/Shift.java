package com.cinema.backend.modal.shift;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.rota.Rota;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Shift extends BaseEntity {
    private String day_of_weed;
    private LocalTime start_time;
    private LocalTime end_time;

    @OneToMany(mappedBy = "shift")
    @JsonBackReference
    private List<Rota> rotas;
}
