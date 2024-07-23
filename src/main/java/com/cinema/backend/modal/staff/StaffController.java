package com.cinema.backend.modal.staff;

import com.cinema.backend.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
public class StaffController {
    public final StaffService staffService;

    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody @Valid StaffRequest staffRequest
    ) {
        return  ResponseEntity.ok(staffService.create(staffRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Integer> update(
            @PathVariable Integer id,
            @RequestBody @Valid StaffRequest staffRequest
    ) {
        return ResponseEntity.ok(staffService.update(id, staffRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        staffService.delete(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/{staff-id}")
    public ResponseEntity<StaffResponse> findById(
            @PathVariable("staff-id") Integer id
    ) {
        return ResponseEntity.ok(staffService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<StaffResponse>> getAll(
        @RequestParam(name = "page", defaultValue = "0", required = true) Integer page,
        @RequestParam(name = "size", defaultValue = "10", required = true) Integer size
    ) {
        return ResponseEntity.ok(staffService.getAll(page, size));
    }
}
