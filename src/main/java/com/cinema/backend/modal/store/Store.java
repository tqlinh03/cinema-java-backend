package com.cinema.backend.modal.store;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.staff.Staff;
import com.cinema.backend.modal.user.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Store extends BaseEntity {
    private String name;
    private String address;
    private Integer phone;


    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Staff> staffs;

    @OneToMany(mappedBy = "store")
    private List<User> users;
}
