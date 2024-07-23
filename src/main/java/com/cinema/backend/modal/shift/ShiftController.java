package com.cinema.backend.modal.shift;

import com.cinema.backend.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
public class ShiftController {
    public final ShiftService shiftService;

    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody @Valid ShiftRequest shiftRequest
    ) {
        return ResponseEntity.ok(shiftService.create(shiftRequest));
    }

    @PatchMapping("/{shift-id}")
    public ResponseEntity<Integer> update(
            @RequestBody @Valid ShiftRequest shiftRequest,
            @PathVariable("shift-id") int id
    ) {
        return ResponseEntity.ok(shiftService.update(id, shiftRequest));
    }

    @DeleteMapping("/{shift-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("shift-id") int id
    ) {
        shiftService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{shift-id})")
    public ResponseEntity<ShiftResponse> findById(
            @PathVariable("shift-id") int id
    ) {
        return ResponseEntity.ok(shiftService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ShiftResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = true) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = true) Integer size
    ) {
        return ResponseEntity.ok(shiftService.findAll(size, page));
    }
}
