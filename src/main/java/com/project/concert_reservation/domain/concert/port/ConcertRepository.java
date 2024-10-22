package com.project.concert_reservation.domain.concert.port;

import com.project.concert_reservation.domain.concert.entity.ConcertEntity;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository {
    ConcertEntity addConcert(ConcertEntity concert);
    List<ConcertEntity> findAll();
    Optional<ConcertEntity> findConcertById(Long id);
}
