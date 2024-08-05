package com.project.concert_reservation.application.concert.service;

import com.project.concert_reservation.domain.concert.entity.ConcertEntity;
import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.concert.model.*;
import com.project.concert_reservation.domain.concert.entity.SeatEntity;
import com.project.concert_reservation.domain.concert.port.*;
import com.project.concert_reservation.mapper.concert.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final PlaceRepository placeRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    private final ConcertMapper concertMapper;
    private final PlaceMapper placeMapper;
    private final ScheduleMapper scheduleMapper;
    private final SeatMapper seatMapper;

    public Concert getConcertInfo(Long concertId){
        Optional<ConcertEntity> optionalConcertEntity = concertRepository.findConcertById(concertId);
        return optionalConcertEntity.map(concertMapper::entityToDomain).orElse(null);
    }
    public List<Place> getPlacesInfo(Long id){
        return placeMapper.entityToDomainBatch(placeRepository.findPlaceById(id));
    }
    public List<Schedule> getSchedulesInfo(Long concertId, Long placeId){
        return scheduleMapper.entityToDomainBatch(scheduleRepository.findScheduleByConcertAndPlace(concertId, placeId));
    }
    public List<Seat> getSeatsInfo(Long scheduleId){
        return seatMapper.entityToDomainBatch(seatRepository.findSeatByScheduleId(scheduleId));
    }
    public Reservation makeReservation(Long userId, Long seatId){
        Optional<SeatEntity> optionalSeat = seatRepository.findSeatById(seatId);
        if (optionalSeat.isPresent()) {
            Seat seat = seatMapper.entityToDomain(optionalSeat.get());

            Reservation reservation = new Reservation();
            reservation.setSeatId(seat.getId());
            reservation.setHolderId(userId);
            reservation.setReservedAt(LocalDateTime.now());
            reservation.setReserved(true);

            return reservation;
        } else {
            return null;
        }
    }

    public SeatEntity getReservedSeatInfo(Long userId){
        Optional<SeatEntity> optionalSeatEntity = seatRepository.findSeatById(reservationRepository.findReservationByHolderId(userId).getFirst().getSeatId());
        return optionalSeatEntity.orElse(null);
    }

    public void setPaidAt(Long reservationId){
        Optional<ReservationEntity> reservation = reservationRepository.findReservationById(reservationId);
        if (reservation.isPresent()) {
            ReservationEntity updatedReservationEntity = reservation.get();
            updatedReservationEntity.setPaidAt(LocalDateTime.now());
            reservationRepository.updateReservation(updatedReservationEntity);
        } else {
            // TODO : Add Error code?
            return;
        }
    }
}
