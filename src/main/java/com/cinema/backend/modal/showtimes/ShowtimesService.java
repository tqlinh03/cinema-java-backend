package com.cinema.backend.modal.showtimes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.movies.MovieRepository;
import com.cinema.backend.modal.movies.entity.Movie;
import com.cinema.backend.modal.showtimes.dto.ShowtimesRequest;
import com.cinema.backend.modal.showtimes.dto.ShowtimesResponse;
import com.cinema.backend.modal.showtimes.entity.Showtimes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowtimesService {
  public final ShowtimesMaper showtimesMaper;
  public final ShowtimesRepository showtimesRepository;
  public final MovieRepository movieRepository;

  public Integer create(ShowtimesRequest showtimesRequest) {
    Showtimes showtimes = showtimesMaper.toShowtimes(showtimesRequest);
    return showtimesRepository.save(showtimes).getId();
  }

  public PageResponse<ShowtimesResponse> findAll(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Showtimes> showtimes = showtimesRepository.findAllShowtimes(pageable);
    List<ShowtimesResponse> showtimesResponses = showtimes.stream()
        .map(showtimesMaper::toShowtimesResponse)
        .toList();
    Meta meta = Meta.builder()
        .number(showtimes.getNumber())
        .size(showtimes.getSize())
        .totalElements(showtimes.getTotalElements())
        .totalPages(showtimes.getTotalPages())
        .last(showtimes.isLast())
        .first(showtimes.isFirst())
        .build();

    return new PageResponse<>(showtimesResponses, meta);
  }

  public List<ShowtimesResponse> findAllByDate(LocalDate date) {
    List<Showtimes> showtimes = showtimesRepository.findAllByDate(date);
    Collections.sort(showtimes, Comparator.comparing(Showtimes::getStart_time));
    return showtimes.stream()
        .map(showtimesMaper::toShowtimesResponse)
        .toList();
  }

  public void delete(Integer id) {
    showtimesRepository.deleteById(id);
  }

  public ShowtimesResponse findById(Integer id) {
    Showtimes showtimes = showtimesRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Showtimes not found"));
    return showtimesMaper.toShowtimesResponse(showtimes);
  }

  public Integer update(ShowtimesRequest showtimesRequest, Integer id) {
    Showtimes showtimes = showtimesRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("không tìm thấy lịch chiếu"));
    Movie movie = movieRepository.findById(showtimesRequest.movie())
        .orElseThrow(() -> new IllegalStateException("Không tìm thấy phim"));

    showtimes.setDate(showtimesRequest.date());
    showtimes.setStart_time(showtimesRequest.start_time());
    showtimes.setEnd_time(showtimesRequest.end_time());
    showtimes.setMovie(movie);
    return showtimesRepository.save(showtimes).getId();
  }

}
