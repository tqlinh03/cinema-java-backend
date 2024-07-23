package com.cinema.backend.modal.user.dto;

import java.time.LocalDate;

public record UserRequest(

    String firstName,

    String lastName,

    LocalDate dateOfBirth,

    Boolean accountLocked,

    Boolean enabled,

    Integer roleId
) {
}
