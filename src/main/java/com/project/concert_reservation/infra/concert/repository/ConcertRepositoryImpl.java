package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    public Concert addConcert(Concert concert){
        concert.setCreatedAt(LocalDateTime.now());
        concert.setUpdatedAt(LocalDateTime.now());

        return concertJpaRepository.save(concert);
    }

    public List<Concert> findAll() { return concertJpaRepository.findAll();}

    public Optional<Concert> findConcertById(Long id){
        return concertJpaRepository.findById(id);
    }
}
