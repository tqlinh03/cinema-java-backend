package com.cinema.backend.modal.rota;

import com.cinema.backend.modal.showtimes.entity.Showtimes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public interface RotaRepository extends JpaRepository<Rota, Integer>,
        JpaSpecificationExecutor<Rota> {

    @Query("""
        SELECT r
        FROM Rota r
        LEFT JOIN r.staffs s
        LEFT JOIN r.shift s2
    """)
    Page<Rota> findAllRota(Pageable pageable);

    @Query("""
        SELECT r
        FROM Rota r
        JOIN r.shift s
        WHERE r.date = :date 
        AND :time > s.start_time
        AND :time < s.end_time
    """)
    Optional<Rota> findByDateAndTime(@Param("date") LocalDate date, @Param("time") LocalTime time);

    List<Rota> findAllByDate(LocalDate date);
}
