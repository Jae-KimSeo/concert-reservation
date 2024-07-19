package com.project.concert_reservation.reservation_service.Business.service;

import com.project.concert_reservation.reservation_service.Business.domain.*;
import com.project.concert_reservation.reservation_service.Infrastructure.Repository.*;
import com.project.concert_reservation.reservation_service.Infrastructure.entity.*;
import com.project.concert_reservation.reservation_service.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
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

    public ConcertDomain getConcertInfo(Long concertId){
        Optional<Concert> optionalConcert = concertRepository.findConcertById(concertId);
        return optionalConcert.map(concertMapper::entityToDomain).orElse(null);
    }
    public List<PlaceDomain> getPlaceInfo(Long id){
        return placeMapper.entityToDomainBatch(placeRepository.findPlaceById(id));
    }
    public List<ScheduleDomain> getScheduleInfo(Long concertId, Long placeId){
        return scheduleMapper.entityToDomainBatch(scheduleRepository.findScheduleByConcertAndPlace(concertId, placeId));
    }
    public List<SeatDomain> getSeatsInfo(Long scheduleId){
        return seatMapper.entityToDomainBatch(seatRepository.findSeatByScheduleId(scheduleId));

    }
    public ReservationDomain makeReservation(Long seatId){
        Optional<Seat> optionalSeat = seatRepository.findSeatById(seatId);
        if (optionalSeat.isPresent()) {
            SeatDomain seatDomain = seatMapper.entityToDomain(optionalSeat.get());

            Reservation reservation = new Reservation();
            reservation.setSeat(seatMapper.domainToEntity(seatDomain));

            return reservationMapper.entityToDomain(reservationRepository.addReservation(reservation));
        } else {
            return null;
        }
    }
}
