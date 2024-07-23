package com.cinema.backend.modal.shift;

import java.time.LocalTime;
import java.util.List;

public record ShiftRequest(
        String day_of_weed,
         LocalTime start_time,
         LocalTime end_time,
        List<Integer> staffIds
) {
}
