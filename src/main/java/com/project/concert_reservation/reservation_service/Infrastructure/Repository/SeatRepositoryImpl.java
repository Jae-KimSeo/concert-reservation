package com.project.concert_reservation.reservation_service.Infrastructure.Repository;

import com.project.concert_reservation.reservation_service.Infrastructure.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SeatRepositoryImpl implements SeatRepository{

    private final SeatJpaRepository seatJpaRepository;

    public Seat addSeat(Seat seat){
        return seatJpaRepository.save(seat);
    }

    public Optional<Seat> findSeatById(Long id){
        return seatJpaRepository.findById(id);
    }

    public List<Seat> findSeatByScheduleId(Long scheduleId){
        return seatJpaRepository.findSeatByScheduleId(scheduleId);
    }

}
