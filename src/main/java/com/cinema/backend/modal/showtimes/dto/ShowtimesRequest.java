package com.cinema.backend.modal.showtimes.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record ShowtimesRequest(
    @NotNull(message = "date cannot be null")
    LocalDate date,
    @NotNull(message = "start_time cannot be null")
    LocalTime start_time,
    @NotNull(message = "end_time cannot be null")
    LocalTime end_time,
    Integer movie,
    Integer roomId

    
) {
}
