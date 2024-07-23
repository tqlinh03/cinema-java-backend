package com.cinema.backend.modal.user.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cinema.backend.modal.role.dto.RoleResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
   private Integer id;
  private String firstName; 
  private String lastName;
  private LocalDate dateOfBirth;
  private String email; 
  private Boolean accountLocked;
  private Boolean enabled;
  private LocalDateTime createDate;
  private LocalDateTime lastModifiedDate;
  private RoleResponse role;
}
