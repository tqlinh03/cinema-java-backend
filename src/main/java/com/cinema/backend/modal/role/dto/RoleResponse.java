package com.cinema.backend.modal.role.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.cinema.backend.modal.permissions.entity.Permissions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
  private Integer id; 
  private String name;
  private String description;
  private boolean isActive;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private List<Permissions> permissions;
}
