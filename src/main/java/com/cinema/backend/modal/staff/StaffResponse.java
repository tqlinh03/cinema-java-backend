package com.cinema.backend.modal.staff;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StaffResponse {
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String img;
    Integer id;
}
