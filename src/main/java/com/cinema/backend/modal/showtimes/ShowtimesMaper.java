package com.cinema.backend.modal.showtimes;

import com.cinema.backend.modal.room.Room;
import com.cinema.backend.modal.room.RoomRepository;
import org.springframework.stereotype.Service;

import com.cinema.backend.modal.movies.MovieRepository;
import com.cinema.backend.modal.movies.dto.MovieResponse;
import com.cinema.backend.modal.showtimes.dto.ShowtimesRequest;
import com.cinema.backend.modal.showtimes.dto.ShowtimesResponse;
import com.cinema.backend.modal.showtimes.entity.Showtimes;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimesMaper {
  public final MovieRepository movieRepository;
  public final RoomRepository roomRepository;
  public final ShowtimesRepository showtimesRepository;

  public Showtimes toShowtimes(ShowtimesRequest request) {
    var movie = movieRepository.findById(request.movie())
    .orElseThrow(() -> new RuntimeException("Movie not found"));
    Room room = roomRepository.findById(request.roomId())
            .orElseThrow(() -> new RuntimeException("Room not found"));
    List<Showtimes> showtimes = showtimesRepository.findAll();
    for(Showtimes showtime : showtimes) {
      if(isOverLapping(showtime, request)) {
        throw  new RuntimeException("Lịch chiếu đã tồn tại!");
      }
    }

    return Showtimes.builder()
            .date(request.date())
            .start_time(request.start_time())
            .end_time(request.end_time())
            .room(room)
            .movie(movie)
            .build();
  }

  public ShowtimesResponse toShowtimesResponse(Showtimes showtimes) {
    MovieResponse movie = MovieResponse.builder()
            .id(showtimes.getMovie().getId())
            .name(showtimes.getMovie().getName())
            .description(showtimes.getMovie().getDescription())
            .img(showtimes.getMovie().getImg())
            .time(showtimes.getMovie().getTime())
            .build();

    return ShowtimesResponse.builder()
            .id(showtimes.getId())
            .date(showtimes.getDate())
            .start_time(showtimes.getStart_time())
            .end_time(showtimes.getEnd_time())
            .movie(movie)
            .room(showtimes.getRoom().getName())
            .roomId(showtimes.getRoom().getId())
            .build();
  }

  public static Boolean isOverLapping(Showtimes showtime, ShowtimesRequest showtimesRequest) {
    return ((showtime.getStart_time()).isBefore(showtimesRequest.end_time()) &&
            (showtime.getEnd_time()).isAfter(showtimesRequest.start_time()) &&
            (showtime.getRoom().getId()).equals(showtimesRequest.roomId()) &&
            (showtime.getDate()).equals(showtimesRequest.date())
    );
  }
}
