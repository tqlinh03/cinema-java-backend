package com.cinema.backend.modal.rota;

import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.showtimes.dto.ShowtimesResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rotas")
@RequiredArgsConstructor
public class RoteController {
    public final RotaService rotaService;

    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody @Valid RotaRequest rotaRequest
    ) {
        return ResponseEntity.ok(rotaService.create(rotaRequest));
    }

    @PatchMapping("/{rota-id}")
    public ResponseEntity<Integer> update(
            @RequestBody @Valid RotaRequest rotaRequest,
            @PathVariable("rota-id") int id
    ) {
        return ResponseEntity.ok(rotaService.update(id, rotaRequest));
    }

    @DeleteMapping("/{rota-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("rota-id") int id
    ) {
        rotaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{rota-id}")
    public ResponseEntity<RotaResponse> findById(
            @PathVariable("rota-id") int id
    ) {
        return ResponseEntity.ok(rotaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<RotaResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = true) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = true) Integer size
    ) {
        return ResponseEntity.ok(rotaService.findAll(page, size));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<RotaResponse>> findAllByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(rotaService.findAllByDate(date));
    }
}
