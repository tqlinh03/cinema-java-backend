package com.cinema.backend.modal.rota;

import com.cinema.backend.modal.staff.Staff;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;

public record RotaRequest(
        @NotNull(message = "Ngày không được để trống.")
        LocalDate date,
        String name,
        Integer shiftId,
        List<Integer> staffIds
) {
}
