package com.cinema.backend.modal.room;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponse {
    Integer id;
    String name;
    String code;
    Boolean isActive;
}
