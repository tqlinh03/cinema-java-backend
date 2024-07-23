package com.cinema.backend.modal.movies.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MovieRequest(
    @NotNull(message = "Tên không được để trống")
    @NotEmpty(message = "Tên không được để trống")
    String name,

    @NotNull(message = "Mô tả không được để trống")
    @NotEmpty(message = "Mô tả không được để trống")
    String description,

    @NotNull(message = "Thể loại không được để trống")
    @NotEmpty(message = "Thể loại không được để trống")
    String genre,

    @NotNull(message = "Thời lượng không được để trống")
    Integer time,

    @NotEmpty(message = "Đạo diễn không được để trống")
    @NotNull(message = "Đạo diễn không được để trống")
    String director,

    @NotEmpty(message = "Diễn viên không được để trống")
    @NotNull(message = "Diễn viên không được để trống")
    String _cast,

    LocalDate releaseDate,

    @NotEmpty(message = "URL video không được để trống")
    @NotNull(message = "URL video không được để trống")
    String videoURL,

    @NotEmpty(message = "Hình ảnh không được để trống")
    @NotNull(message = "Hình ảnh không được để trống")
    String img) {
}