package com.cinema.backend.modal.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cinema.backend.modal.user.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  @Query("""
      SELECT user
      FROM User user
      """)
  Page<User> findAllUser(Pageable pageable);
}
