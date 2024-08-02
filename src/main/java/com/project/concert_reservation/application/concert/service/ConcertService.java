package com.project.concert_reservation.application.concert.service;

import com.project.concert_reservation.domain.concert.entity.ConcertEntity;
import com.project.concert_reservation.domain.concert.entity.ReservationEntity;
import com.project.concert_reservation.domain.concert.model.Concert;
import com.project.concert_reservation.domain.concert.model.Place;
import com.project.concert_reservation.domain.concert.model.Schedule;
import com.project.concert_reservation.domain.concert.dto.ReservationDTO;
import com.project.concert_reservation.domain.concert.dto.SeatDTO;
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
    private final ReservationMapper reservationMapper;

    public Concert getConcertInfo(Long concertId){
        Optional<ConcertEntity> optionalConcertEntity = concertRepository.findConcertById(concertId);
        return optionalConcertEntity.map(concertMapper::entityToDomain).orElse(null);
    }
    public List<Place> getPlaceInfo(Long id){
        return placeMapper.entityToDomainBatch(placeRepository.findPlaceById(id));
    }
    public List<Schedule> getScheduleInfo(Long concertId, Long placeId){
        return scheduleMapper.entityToDomainBatch(scheduleRepository.findScheduleByConcertAndPlace(concertId, placeId));
    }
    public List<SeatDTO> getSeatsInfo(Long scheduleId){
        return seatMapper.entityToDTOBatch(seatRepository.findSeatByScheduleId(scheduleId));

    }
    public ReservationDTO makeReservation(Long seatId){
        Optional<SeatEntity> optionalSeat = seatRepository.findSeatById(seatId);
        if (optionalSeat.isPresent()) {
            SeatDTO seatDTO = seatMapper.entityToDTO(optionalSeat.get());

            ReservationEntity reservationEntity = new ReservationEntity();
            reservationEntity.setSeatEntity(seatMapper.dtoToEntity(seatDTO));

            return reservationMapper.entityToDTO(reservationRepository.addReservation(reservationEntity));
        } else {
            return null;
        }
    }

    public SeatEntity getReservedSeatInfo(Long userId){
        return reservationRepository.findReservationByHolderId(userId).getFirst().getSeatEntity();
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
