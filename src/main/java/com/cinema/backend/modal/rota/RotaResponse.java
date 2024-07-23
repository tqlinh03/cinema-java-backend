package com.cinema.backend.modal.rota;

import com.cinema.backend.modal.shift.Shift;
import com.cinema.backend.modal.staff.Staff;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
public class RotaResponse {
    Integer id;
    String name;
    LocalDate date;
    List<Staff> staffs;
    Shift shift;
}
