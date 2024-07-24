package com.cinema.backend.modal.staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> ,
        JpaSpecificationExecutor<Staff> {

    @Query("""
        SELECT staff
        FROM Staff staff
        WHERE staff.isDeleted = false
    """)
    Page<Staff> findAllStaffs(Pageable pageable);
}
