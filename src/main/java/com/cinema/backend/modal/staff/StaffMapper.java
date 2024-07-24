package com.cinema.backend.modal.staff;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffMapper {
    private final PasswordEncoder passwordEncoder;

    public Staff toStaff(StaffRequest staffRequest) {
        return Staff.builder()
                .firstName(staffRequest.firstName())
                .lastName(staffRequest.lastName())
                .email(staffRequest.email())
                .isDeleted(false)
                .password(passwordEncoder.encode(staffRequest.password()))
                .phone(staffRequest.phone())
                .address(staffRequest.address())
                .img(staffRequest.img())
                .position(staffRequest.position())
                .hourly_rate(staffRequest.hourly_rate())
                .gender(staffRequest.gender())
                .build();
    }

    public StaffResponse toStaffResponse(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email((staff.getEmail()))
                .phone(staff.getPhone())
                .address(staff.getAddress())
                .img(staff.getImg())
                .build();

    }

}
