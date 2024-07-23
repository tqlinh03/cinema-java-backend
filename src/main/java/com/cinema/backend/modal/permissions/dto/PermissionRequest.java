package com.cinema.backend.modal.permissions.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PermissionRequest(
    @NotNull(message = "Tên không được để trống") 
    @NotEmpty(message = "Tên không được để trống") 
    String name,

    @NotNull(message = "apiPath không được để trống") 
    @NotEmpty(message = "apiPath không được để trống") 
    String apiPath,

    @NotNull(message = "method không được để trống") 
    @NotEmpty(message = "method không được để trống") 
    String method,

    @NotNull(message = "Module không được để trống") 
    @NotEmpty(message = "Module không được để trống") 
    String module
) {

}
