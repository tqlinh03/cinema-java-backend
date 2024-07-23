package com.cinema.backend.modal.room;

import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    public Room toRoom(RoomRequest roomRequest) {
        return Room.builder()
                .name(roomRequest.name())
                .code(roomRequest.code())
                .isActive(roomRequest.isActive())
                .build();
    }

    public RoomResponse toRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .code(room.getCode())
                .isActive(room.getIsActive())
                .build();
    }

}
