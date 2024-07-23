package com.cinema.backend.modal.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> ,
        JpaSpecificationExecutor<Staff> {

}
