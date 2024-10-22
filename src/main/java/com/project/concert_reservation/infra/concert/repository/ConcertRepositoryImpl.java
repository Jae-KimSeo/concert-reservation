package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.ConcertEntity;
import com.project.concert_reservation.domain.concert.port.ConcertRepository;
import com.project.concert_reservation.infra.concert.repository.orm.ConcertJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    public ConcertEntity addConcert(ConcertEntity concert){
        concert.setCreatedAt(LocalDateTime.now());
        concert.setUpdatedAt(LocalDateTime.now());

        return concertJpaRepository.save(concert);
    }

    public List<ConcertEntity> findAll() { return concertJpaRepository.findAll();}

    public Optional<ConcertEntity> findConcertById(Long id){
        return concertJpaRepository.findById(id);
    }
}
