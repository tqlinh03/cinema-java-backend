package com.cinema.backend.modal.rota;

import com.cinema.backend.common.Meta;
import com.cinema.backend.common.PageResponse;
import com.cinema.backend.modal.shift.Shift;
import com.cinema.backend.modal.showtimes.entity.Showtimes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {
    public final RotaMapper rotaMapper;
    public final RotaRepository rotaRepository;

    public void delete(int id) {
        rotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found rota"));
        rotaRepository.deleteById(id);
    }

    public Integer create(RotaRequest rotaRequest) {
        Rota rota = rotaMapper.toRota(rotaRequest);
        return rotaRepository.save(rota).getId();
    }

    public Integer update(int id, RotaRequest rotaRequest) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        Rota rotaUpdate = rotaMapper.toSetRota(rota,rotaRequest);
        return rotaRepository.save(rotaUpdate).getId();
    }

    public RotaResponse findById(int id) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found "));
        return rotaMapper.toRotaResponse(rota);
    }

    public PageResponse<RotaResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Rota> rota  = rotaRepository.findAllRota(pageable);
        List<RotaResponse> rotaResponses = rota.stream()
                .map(rotaMapper::toRotaResponse)
                .toList();
        Meta meta = Meta.builder()
                .number(rota.getNumber())
                .size(rota.getSize())
                .totalElements(rota.getTotalElements())
                .totalPages(rota.getTotalPages())
                .last(rota.isLast())
                .first(rota.isFirst())
                .build();
        return new PageResponse<>(rotaResponses, meta);
    }

    public Rota currentRota(LocalDate date, LocalTime time) {
        Rota rota = rotaRepository.findByDateAndTime(date, time)
                .orElse(null);
        return rota;
    }

    public List<RotaResponse> findAllByDate(LocalDate date) {
        List<Rota> rotaList = rotaRepository.findAllByDate(date);
        return rotaList.stream()
                .sorted(Comparator.comparing(rota -> rota.getShift().getStart_time()))
                .map(rotaMapper::toRotaResponse)
                .toList();
    }
}
