package com.cinema.backend.modal.shift;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {
    public final ShiftRopository shiftRopository;
    public final ShiftMapper shiftMapper;

    public Integer create(ShiftRequest shiftRequest) {
        Shift shift = shiftMapper.toShift(shiftRequest);
        return shiftRopository.save(shift).getId();
    }

    public Integer update(int id, ShiftRequest shiftRequest) {
        Shift shift = shiftRopository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shift not found"));
        shift.setDay_of_weed(shiftRequest.day_of_weed());
        shift.setStart_time(shiftRequest.start_time());
        shift.setEnd_time(shiftRequest.end_time());
        return shiftRopository.save(shift).getId();
    }

    public void delete(int id) {
        shiftRopository.deleteById(id);
    }

    public ShiftResponse findById(int id) {
        Shift shift = shiftRopository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shift not found"));
        return shiftMapper.toResponse(shift);
    }

    public PageResponse<ShiftResponse> findAll(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate"));
        Page<Shift> shifts = shiftRopository.findAll(pageable);
        List<ShiftResponse> ShhiftResponse = shifts.stream()
                .map(shiftMapper::toResponse)
                .toList();
        Meta meta = Meta.builder()
                .number(shifts.getNumber())
                .size(shifts.getSize())
                .totalElements(shifts.getTotalElements())
                .totalPages(shifts.getTotalPages())
                .last(shifts.isLast())
                .first(shifts.isFirst())
                .build();
        return new PageResponse<>(ShhiftResponse, meta);
    }
}
