package com.cinema.backend.modal.movies.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cinema.backend.common.BaseEntity;
import com.cinema.backend.modal.showtimes.entity.Showtimes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
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
public class Movie extends BaseEntity {
  @Column(unique = true)
  private String name;
  private String description; 
  private String genre;
  private Integer time;
  private String director;
  private String _cast;
  private LocalDate releaseDate;
  private String videoURL;
  private String img;
  
  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Showtimes> showtimes;
}
