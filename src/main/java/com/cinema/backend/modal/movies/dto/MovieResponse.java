package com.cinema.backend.modal.movies.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.cinema.backend.modal.showtimes.dto.ShowtimesResponse;
import com.cinema.backend.modal.showtimes.entity.Showtimes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MovieResponse {
  private Integer id;
  private String name;
  private String description; 
  private String genre;
  private Integer time;
  private String director;
  private String _cast;
  private LocalDate releaseDate;
  private String videoURL;
  private String img;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  List<ShowtimesResponse> showtime;
}
