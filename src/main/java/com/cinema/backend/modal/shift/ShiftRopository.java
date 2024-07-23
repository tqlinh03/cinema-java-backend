package com.cinema.backend.modal.shift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiftRopository extends JpaRepository<Shift, Integer> ,
        JpaSpecificationExecutor<Shift> {
}
