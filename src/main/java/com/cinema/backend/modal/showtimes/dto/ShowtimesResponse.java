package com.cinema.backend.modal.showtimes.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cinema.backend.modal.movies.dto.MovieResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShowtimesResponse {
  Integer id;
  LocalDate date;
  LocalTime start_time;
  LocalTime end_time;
  MovieResponse movie;
  String room;
  Integer roomId;
}
