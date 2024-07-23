package com.cinema.backend.modal.showtimes;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cinema.backend.modal.showtimes.entity.Showtimes;

public interface ShowtimesRepository extends 
JpaRepository<Showtimes, Integer>, JpaSpecificationExecutor<Showtimes>{
  @Query("""
      SELECT movie
      FROM Movie movie
      """)
  Page<Showtimes> findAllShowtimes(Pageable pageable);

  List<Showtimes> findAllByDate(LocalDate date);

}
