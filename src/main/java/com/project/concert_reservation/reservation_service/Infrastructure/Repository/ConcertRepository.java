package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Concert;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository {
    Concert addConcert(Concert concert);
    List<Concert> findAll();
    Optional<Concert> findConcertById(Long id);
}
