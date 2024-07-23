package com.cinema.backend.modal.role;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cinema.backend.modal.role.entities.Role;

public interface RoleRepository extends
    JpaRepository<Role, Integer>,
    JpaSpecificationExecutor<Role> {
  @Query("""
      SELECT role
      FROM Role role
      """)
  Page<Role> findAllRoles(Pageable pageable);

  Optional<Role> findByName(String role);

}
