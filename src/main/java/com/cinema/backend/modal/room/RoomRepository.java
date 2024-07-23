package com.cinema.backend.modal.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomRepository extends JpaRepository<Room, Integer>,
        JpaSpecificationExecutor<Room> {

}
