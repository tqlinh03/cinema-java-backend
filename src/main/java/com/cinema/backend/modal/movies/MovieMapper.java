package com.cinema.backend.modal.movies;

import com.cinema.backend.modal.showtimes.ShowtimesMaper;
import com.cinema.backend.modal.showtimes.dto.ShowtimesResponse;
import com.cinema.backend.modal.showtimes.entity.Showtimes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cinema.backend.modal.movies.dto.MovieRequest;
import com.cinema.backend.modal.movies.dto.MovieResponse;
import com.cinema.backend.modal.movies.entity.Movie;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieMapper {
  public final ShowtimesMaper showtimesMaper;

  public Movie toMovie(MovieRequest movieRequest) {
    return Movie.builder()
            .name(movieRequest.name())
            .description(movieRequest.description())
            .genre(movieRequest.genre())
            .time(movieRequest.time())
            .director(movieRequest.director())
            ._cast(movieRequest._cast())
            .releaseDate(movieRequest.releaseDate())
            .videoURL(movieRequest.videoURL())
            .img(movieRequest.img())
            .build();
  }

  public MovieResponse toMovieResponse(Movie movie) {
    List<ShowtimesResponse> showtime = movie.getShowtimes().stream()
                    .map(showtimesMaper::toShowtimesResponse)
                    .toList();

    return MovieResponse.builder()
            .id(movie.getId())
            .name(movie.getName())
            .description(movie.getDescription())
            .genre(movie.getGenre())
            .time(movie.getTime())
            .director(movie.getDirector())
            ._cast(movie.get_cast())
            .releaseDate(movie.getReleaseDate())
            .videoURL(movie.getVideoURL())
            .img(movie.getImg())
            .createdDate(movie.getCreatedDate())
            .lastModifiedDate(movie.getLastModifiedDate())
            .showtime(showtime)
            .build();
  }
}
