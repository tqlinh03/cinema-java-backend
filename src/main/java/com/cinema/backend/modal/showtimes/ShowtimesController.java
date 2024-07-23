package com.cinema.backend.modal.showtimes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.showtimes.dto.ShowtimesRequest;
import com.cinema.backend.modal.showtimes.dto.ShowtimesResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/showtimes")
@Tag(name = "Showtimes")
public class ShowtimesController {
  public final ShowtimesService showtimesService;

  @PostMapping
  public ResponseEntity<Integer> create(
    @RequestBody @Valid ShowtimesRequest showtimesRequest
  ) {
    return ResponseEntity.ok(showtimesService.create(showtimesRequest));
  } 

  @GetMapping
  public ResponseEntity<PageResponse<ShowtimesResponse>> findAll(
    @RequestParam(name = "page", defaultValue ="0", required = false) Integer page,
    @RequestParam(name = "size", defaultValue ="10", required = false) Integer size
  ) {
    return ResponseEntity.ok(showtimesService.findAll(page, size));
  }

  @GetMapping("/{date}")
  public ResponseEntity<List<ShowtimesResponse>> findAllByDate(
    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
  ) {
    return ResponseEntity.ok(showtimesService.findAllByDate(date));
  }

  @GetMapping("/detail/{id}")
  public ResponseEntity<ShowtimesResponse> findById(
    @PathVariable Integer id
  ) {
      return ResponseEntity.ok(showtimesService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(
    @PathVariable Integer id
  ) {
    showtimesService.delete(id);
    return ResponseEntity.accepted().body("Xóa thành công lịch chiếu ID = " + id);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Integer> update(
    @RequestBody @Valid ShowtimesRequest showtimesRequest,
    @PathVariable Integer id
  ) {
    return ResponseEntity.ok(showtimesService.update(showtimesRequest, id));
  }


}
