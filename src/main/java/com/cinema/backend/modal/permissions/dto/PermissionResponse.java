package com.cinema.backend.modal.permissions.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponse {
  private Integer id;
  private String name;
  private String apiPath;
  private String method;
  private String module;

  private LocalDateTime createdDate;

}
