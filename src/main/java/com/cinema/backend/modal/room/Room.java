package com.cinema.backend.modal.room;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.showtimes.entity.Showtimes;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Room extends BaseEntity {
    private String name;
    private String code;
    private Boolean isActive;

    @OneToMany(mappedBy = "room")
     private List<Showtimes> showtimes;
}
