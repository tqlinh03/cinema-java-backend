package com.cinema.backend.modal.permissions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cinema.backend.modal.permissions.entity.Permissions;

public interface PermissionsRepository extends
    JpaRepository<Permissions, Integer>,
    JpaSpecificationExecutor<Permissions> {
      
    @Query("""
        SELECT permissions 
        FROM Permissions permissions 
    """)
    Page<Permissions> findAllPermissions(Pageable pageable);
}
