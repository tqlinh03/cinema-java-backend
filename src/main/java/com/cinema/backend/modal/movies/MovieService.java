package com.cinema.backend.modal.movies;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.movies.dto.MovieRequest;
import com.cinema.backend.modal.movies.dto.MovieResponse;
import com.cinema.backend.modal.movies.entity.Movie;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
  public final MovieRepository movieRepository;
  public final MovieMapper movieMapper;

  public String create(MovieRequest movieRequest) {
    Movie movie = movieMapper.toMovie(movieRequest);
    return movieRepository.save(movie).getName();
  }

  public PageResponse<MovieResponse> getAll(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Movie> movies = movieRepository.findAll(pageable);
    List<MovieResponse> movieResponses = movies.stream()
        .map(movieMapper::toMovieResponse)
        .toList();
    Meta meta = Meta.builder()
        .number(movies.getNumber())
        .size(movies.getSize())
        .totalElements(movies.getTotalElements())
        .totalPages(movies.getTotalPages())
        .last(movies.isLast())
        .first(movies.isFirst())
        .build();

    return new PageResponse<>(movieResponses, meta);
  }

  public MovieResponse getMovieById(Integer id) {
    return movieRepository.findById(id)
      .map(movieMapper::toMovieResponse)
      .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Movie id =" + id));
  }

  public String updateMovie(Integer id, @Valid MovieRequest movieRequest) {
    Movie movie = movieRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Movie có id =" + id));
    movie.setName(movieRequest.name());
    movie.setDescription(movieRequest.description());
    movie.setGenre(movieRequest.genre());
    movie.setTime(movieRequest.time());
    movie.setDirector(movieRequest.director());
    movie.set_cast(movieRequest._cast());
    movie.setReleaseDate(movieRequest.releaseDate());
    movie.setVideoURL(movieRequest.videoURL());
    movie.setImg(movieRequest.img());
    return movieRepository.save(movie).getName();
  }

  public String deleteMovie(Integer id) {
    Movie movie = movieRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Movie có id =" + id));
    movieRepository.delete(movie);
    return "Xóa thành công phim: " + movie.getName();
  }

  public MovieResponse findMovieByIdAndDate(Integer id, LocalDate date) {
    Movie movie = movieRepository.findMovieByIdAndDate(id, date);
    System.out.println("========="+movie.getShowtimes());
    return movieMapper.toMovieResponse(movie);
  }
}
