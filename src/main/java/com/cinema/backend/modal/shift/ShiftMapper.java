package com.cinema.backend.modal.shift;

import org.springframework.stereotype.Service;

@Service
public class ShiftMapper {
    public Shift toShift(ShiftRequest shift) {
        return Shift.builder()
                .day_of_weed(shift.day_of_weed())
                .start_time(shift.start_time())
                .end_time(shift.end_time())
                .build();
    }

    public ShiftResponse toResponse(Shift shift) {
        return ShiftResponse.builder()
                .id(shift.getId())
                .day_of_weed(shift.getDay_of_weed())
                .start_time(shift.getStart_time())
                .end_time(shift.getEnd_time())
                .created_at(shift.getCreatedDate())
                .build();
    }


}
