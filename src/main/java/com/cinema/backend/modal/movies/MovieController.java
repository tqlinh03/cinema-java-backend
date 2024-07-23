package com.cinema.backend.modal.movies;

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
import com.cinema.backend.modal.movies.dto.MovieRequest;
import com.cinema.backend.modal.movies.dto.MovieResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies") 
@Tag(name = "Movie")
public class MovieController {
  public final MovieService movieService;

  @PostMapping
  public ResponseEntity<String> create(
    @RequestBody @Valid MovieRequest movieRequest
  ) {
    return ResponseEntity.ok(movieService.create(movieRequest));
  }

  @GetMapping
  public ResponseEntity<PageResponse<MovieResponse>> getAllMovies(
     @RequestParam(name = "page", defaultValue ="0", required = false) Integer page,
    @RequestParam(name = "size", defaultValue ="10", required = false) Integer size
  ) {
    return ResponseEntity.ok(movieService.getAll(page, size));
  }

  @GetMapping("/{id}") 
  public ResponseEntity<MovieResponse> getMovieById(
    @PathVariable Integer id
  ) {
    return ResponseEntity.ok(movieService.getMovieById(id));
  }
    @GetMapping("/movie-showtime/{id}/{date}")
  public ResponseEntity<MovieResponse> findMovieByIdAndDate(
          @PathVariable("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
          @PathVariable("id") Integer id
  ) {
    return ResponseEntity.ok(movieService.findMovieByIdAndDate(id, date));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> updateMovie(
    @PathVariable Integer id,
    @RequestBody @Valid MovieRequest movieRequest
  ) {
    return ResponseEntity.ok(movieService.updateMovie(id, movieRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMovie(
    @PathVariable Integer id
  ) {
    
    return ResponseEntity.ok(movieService.deleteMovie(id));
  }
}
