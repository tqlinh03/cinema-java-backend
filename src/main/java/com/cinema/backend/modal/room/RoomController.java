package com.cinema.backend.modal.room;

import com.cinema.backend.common.PageResponse;
import jakarta.persistence.EntityListeners;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rooms")
public class RoomController {
    public final RoomService roomService;

    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody @Valid RoomRequest roomRequest
    ) {
        return ResponseEntity.ok(roomService.create(roomRequest));
    }

    @GetMapping
    public ResponseEntity<PageResponse<RoomResponse>> findAllPage(
            @RequestParam(name = "page", defaultValue = "0", required = true) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = true) Integer size
    ) {
        return ResponseEntity.ok(roomService.findAllPage(size, page));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomResponse>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roomService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> update(
            @PathVariable Integer id,
            @RequestBody @Valid RoomRequest roomRequest
    ) {
        return ResponseEntity.ok(roomService.update(id, roomRequest));
    }
}
