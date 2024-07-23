package com.cinema.backend.modal.role.dto;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record RoleRequest(
    @NotNull(message = "Tên không được để trống")
    @NotEmpty(message = "Tên không được để trống")
    String name,
    @NotNull(message = "Mô tả không được để trống")
    @NotEmpty(message = "Mô tả không được để trống")
    String description,

    boolean isActive,
    List<Integer> permissions
 ) {
  
}
