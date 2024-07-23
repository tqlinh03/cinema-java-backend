package com.cinema.backend.modal.rota;

import com.cinema.backend.modal.shift.Shift;
import com.cinema.backend.modal.shift.ShiftRopository;
import com.cinema.backend.modal.staff.Staff;
import com.cinema.backend.modal.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaMapper {
    public final ShiftRopository shiftRopository;
    public final RotaRepository rotaRepository;
    private final StaffRepository staffRepository;

    public Rota toRota(RotaRequest rotaRequest) {
        Shift shift = null;
        List<Staff> staffs = new ArrayList<>();

        if(rotaRequest.shiftId() != null) {
            shift = shiftRopository.findById(rotaRequest.shiftId())
                    .orElseThrow(() -> new RuntimeException("Shift not found"));
        }
        if(rotaRequest.staffIds() != null) {
            for ( Integer staffId : rotaRequest.staffIds()) {
                Staff staff = staffRepository.findById(staffId)
                        .orElseThrow(() -> new RuntimeException("Staff not found"));
                staffs.add(staff);
            }
        }

        return Rota.builder()
                .name(rotaRequest.name())
                .date(rotaRequest.date())
                .shift(shift)
                .staffs(staffs)
                .build();
    }

    public  RotaResponse toRotaResponse(Rota rota) {
        return RotaResponse.builder()
                .id(rota.getId())
                .name(rota.getName())
                .date(rota.getDate())
                .shift(rota.getShift())
                .staffs(rota.getStaffs())
                .build();
    }

    public Rota toSetRota(Rota rota, RotaRequest rotaRequest) {
        List<Staff> staffs = new ArrayList<>();

        rota.setDate(rotaRequest.date());
        rota.setName(rotaRequest.name());
        if (rotaRequest.shiftId() != null) {
            Shift shift = shiftRopository.findById(rotaRequest.shiftId())
                            .orElseThrow(() -> new RuntimeException("Shift not found"));
            rota.setShift(shift);
        }
        if (rotaRequest.staffIds() != null) {
            for (Integer staffId : rotaRequest.staffIds()) {
                Staff staff = staffRepository.findById(staffId)
                        .orElseThrow(() -> new RuntimeException("Staff not found"));
                staffs.add(staff);
            }
            rota.setStaffs(staffs);
        }
        return rota;
    }
}
