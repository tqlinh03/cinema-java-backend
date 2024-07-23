package com.cinema.backend.modal.staff;


import jakarta.validation.constraints.NotNull;

public record StaffRequest (
        String firstName,
        String lastName,
        @NotNull(message = "Email không được để trống.")
        String email,
        @NotNull(message = "Mật khẩu không được để trống.")
        String password,
        String phone,
        String address,
        String img,
        String position,
        Float hourly_rate,
        String gender
) {

}
