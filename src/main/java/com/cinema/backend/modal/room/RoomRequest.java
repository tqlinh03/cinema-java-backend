package com.cinema.backend.modal.room;


public record RoomRequest (
        String name,
        String code,
        Boolean isActive
) {

}
