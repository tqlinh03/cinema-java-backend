package com.cinema.backend.modal.movies;

import java.time.LocalDate;
import java.util.Optional;

import com.cinema.backend.modal.movies.dto.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cinema.backend.modal.movies.entity.Movie;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends 
JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie>{
  Optional<Movie> findByName(String name);

  @Query("""
      SELECT movie
      FROM Movie movie
      """)
  Page<Movie> findAllMovies(Pageable pageable);

  @Query("""
        SELECT m
        FROM Movie m
        JOIN FETCH m.showtimes s
        WHERE m.id = :id AND s.date = :date
    """)
  Movie findMovieByIdAndDate(@Param("id") Integer id, @Param("date") LocalDate date);
}
