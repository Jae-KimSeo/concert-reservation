package com.project.concert_reservation.infra.concert.repository;

import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import com.project.concert_reservation.domain.concert.port.SeatRepository;
import com.project.concert_reservation.infra.concert.repository.orm.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;

    public SeatEntity addSeat(SeatEntity seatEntity){
        return seatJpaRepository.save(seatEntity);
    }

    public Optional<SeatEntity> findSeatById(Long id){
        return seatJpaRepository.findById(id);
    }

    public List<SeatEntity> findSeatByScheduleId(Long scheduleId){
        return seatJpaRepository.findSeatByScheduleId(scheduleId);
    }

}
