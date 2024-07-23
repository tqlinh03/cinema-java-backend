package com.cinema.backend.modal.showtimes.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cinema.backend.modal.room.Room;
import com.cinema.backend.modal.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.movies.entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Showtimes extends BaseEntity {
  private LocalDate date;
  private LocalTime start_time;
  private LocalTime end_time;

  @ManyToOne
  @JoinColumn(name = "movie_id", referencedColumnName = "id")
  @JsonBackReference
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "room_id", referencedColumnName = "id")
  private Room room;

  @OneToMany(mappedBy = "showtime")
  private List<Ticket> tickets;
}
