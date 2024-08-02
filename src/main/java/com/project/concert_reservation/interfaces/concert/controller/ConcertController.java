package com.project.concert_reservation.interfaces.concert.controller;


import com.project.concert_reservation.domain.concert.dto.SeatDTO;
import com.project.concert_reservation.domain.concert.model.Seat;
import com.project.concert_reservation.interfaces.concert.controller.dto.*;
import com.project.concert_reservation.domain.concert.model.Concert;
import com.project.concert_reservation.domain.concert.model.Place;
import com.project.concert_reservation.domain.concert.model.Schedule;
import com.project.concert_reservation.application.concert.service.ConcertService;
import com.project.concert_reservation.domain.concert.port.ReservationRepository;
import com.project.concert_reservation.mapper.concert.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertController {

    private final ConcertService concertService;

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @GetMapping("/places/{concertId}")
    public ResponseEntity<PlacesResponse> getAvailableResponse(@PathVariable Long concertId) {
        if (concertId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> placeIds = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> capacities = new ArrayList<>();

        List<Place> places = concertService.getPlaceInfo(concertId);

        if (places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (Place place : places){
            placeIds.add(place.getId());
            names.add(place.getName());
            capacities.add(place.getCapacity());
        }

        PlacesResponse response = new PlacesResponse();
        response.setPlaceIds(placeIds);
        response.setNames(names);
        response.setCapacities(capacities);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/schedules/{concertId}")
    public ResponseEntity<SchedulesResponse> getAvailableDates(@PathVariable Long concertId) {
        if (concertId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Place> places = concertService.getPlaceInfo(concertId);

        if (places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> scheduleIdList = new ArrayList<>();
        List<LocalDateTime> scheduleDateList = new ArrayList<>();
        for (Place place : places){
            List<Schedule> schedules = concertService.getScheduleInfo(concertId, place.getId());
            for (Schedule schedule : schedules){
                scheduleIdList.add(schedule.getId());
                scheduleDateList.add(schedule.getEventDate());
            }
        }

        SchedulesResponse response = new SchedulesResponse();
        response.setScheduleIds(scheduleIdList);
        response.setScheduleDates(scheduleDateList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/seats/{scheduleId}")
    public ResponseEntity<SeatsResponse> getAvailableSeats(@PathVariable Long scheduleId) {

        List<SeatDTO> seats = concertService.getSeatsInfo(scheduleId);

        SeatsResponse response = new SeatsResponse();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/seats")
    public ResponseEntity<ReservationResponse> reserveSeat(@RequestHeader("Authorization") String authorization,
                                                           @RequestBody ReservationRequest request) {
        if (request.getScheduleId()  == null ||
        request.getSeatId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ReservationResponse response = reservationMapper.domainToResponse(concertService.makeReservation(request.getSeatId()));
        response.setReserved(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}