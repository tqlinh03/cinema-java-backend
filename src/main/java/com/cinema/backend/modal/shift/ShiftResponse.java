package com.cinema.backend.modal.shift;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
public class ShiftResponse {
    Integer id;
    String day_of_weed;
    LocalTime start_time;
    LocalTime end_time;
    LocalDateTime created_at;
}
