package com.cinema.backend.modal.staff;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.movies.entity.Movie;
import com.cinema.backend.modal.store.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    public final StoreRepository storeRepository;
    public final StaffRepository staffRepository;
    public final StaffMapper staffMapper;

    public Integer create(StaffRequest staffRequest) {
        Staff staff = staffMapper.toStaff(staffRequest);
        return staffRepository.save(staff).getId();
    }

    public Integer update(Integer id, StaffRequest staffRequest) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("không tìm thấy nhân viên có Id = " + id));
        staff.setFirstName(staffRequest.firstName());
        staff.setLastName(staffRequest.lastName());
        staff.setAddress(staffRequest.address());
        staff.setPhone(staffRequest.phone());
        return staffRepository.save(staff).getId();
    }


    public void delete(Integer id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("không tìm thấy nhân viên có id = " + id));
        staffRepository.delete(staff);
    }

    public StaffResponse findById(Integer id) {
        return staffRepository.findById(id)
                .map(staffMapper::toStaffResponse)
                .orElseThrow(() -> new EntityNotFoundException("không tìm thấy nhân viên có id = " + id));
    }

    public PageResponse<StaffResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate"));
        Page<Staff> staffPage = staffRepository.findAll(pageable);
        List<StaffResponse> staffResponses = staffPage.stream()
                .map(staffMapper::toStaffResponse)
                .toList();
        Meta meta = Meta.builder()
                .number(staffPage.getNumber())
                .size(staffPage.getSize())
                .totalElements(staffPage.getTotalElements())
                .totalPages(staffPage.getTotalPages())
                .last(staffPage.isLast())
                .first(staffPage.isFirst())
                .build();
        return new PageResponse<>(staffResponses, meta);
    }
}
