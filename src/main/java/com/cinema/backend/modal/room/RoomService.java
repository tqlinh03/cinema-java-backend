package com.cinema.backend.modal.room;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.movies.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    public final RoomRepository roomRepository;
    public  final RoomMapper roomMapper;

    public Integer create(RoomRequest roomRequest) {
        Room room = roomMapper.toRoom(roomRequest);
        return roomRepository.save(room).getId();
    }

    public PageResponse<RoomResponse> findAllPage(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> rooms = roomRepository.findAll(pageable);
        List<RoomResponse> roomResponses = rooms.getContent().stream()
                .map(roomMapper::toRoomResponse)
                .toList();
        Meta meta =  Meta.builder()
                .number(rooms.getNumber())
                .size(rooms.getSize())
                .totalElements(rooms.getTotalElements())
                .totalPages(rooms.getTotalPages())
                .last(rooms.isLast())
                .first(rooms.isFirst())
                .build();
        return new PageResponse<>(roomResponses, meta);
    }

    public  List<RoomResponse> findAll() {
        List<RoomResponse> rooms = roomRepository.findAll().stream()
                .map(roomMapper::toRoomResponse)
                .toList();
        return rooms;
    }

    public RoomResponse findById(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("room not found"));
        return roomMapper.toRoomResponse(room);
    }

    public void delete(Integer id) {
        roomRepository.deleteById(id);
    }

    public Integer update(Integer id, RoomRequest roomRequest) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("room not found"));
        room.setName(roomRequest.name());
        room.setCode(roomRequest.code());
        room.setIsActive(roomRequest.isActive());
        return roomRepository.save(room).getId();
    }

}
