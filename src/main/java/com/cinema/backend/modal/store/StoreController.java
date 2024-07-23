package com.cinema.backend.modal.store;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    public final StoreService storeService;

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody @Valid StoreRequest storeRequest) {
        return ResponseEntity.ok(storeService.create(storeRequest));
    }

    @PatchMapping("/{store-id}")
    public ResponseEntity<Integer> update(
            @RequestBody @Valid StoreRequest storeRequest,
            @PathVariable("store-id") Integer id
    ) {
        return ResponseEntity.ok(storeService.update(id, storeRequest));
    }
}
