package com.project.concert_reservation.interfaces.concert.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.concert_reservation.domain.concert.dto.ConcertCreateResponse;
import com.project.concert_reservation.domain.concert.model.Concert;
import com.project.concert_reservation.domain.concert.model.Seat;
import com.project.concert_reservation.domain.queue.domain.UserDomain;
import com.project.concert_reservation.interfaces.concert.controller.dto.*;
import com.project.concert_reservation.domain.concert.model.Place;
import com.project.concert_reservation.domain.concert.model.Schedule;
import com.project.concert_reservation.application.concert.service.ConcertService;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateRequest;
import com.project.concert_reservation.interfaces.queue.controller.dto.UserCreateResponse;
import com.project.concert_reservation.mapper.concert.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/concerts")
public class ConcertController {
    private final ConcertService concertService;
    private final ReservationMapper reservationMapper;
    private final ObjectMapper objectMapper;

    @GetMapping("/places/{concertId}")
    public ResponseEntity<PlacesResponse> getAvailableResponse(@PathVariable Long concertId) {
        if (concertId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> placeIds = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> capacities = new ArrayList<>();

        List<Place> places = concertService.getPlacesInfo(concertId);

        if (places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (Place place : places) {
            placeIds.add(place.getId());
            names.add(place.getName());
            capacities.add(place.getCapacity());
        }

        PlacesResponse response = new PlacesResponse();
        response.setIds(placeIds);
        response.setNames(names);
        response.setCapacities(capacities);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/schedules/{concertId}")
    public ResponseEntity<SchedulesResponse> getAvailableDates(@PathVariable Long concertId) {
        if (concertId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Place> places = concertService.getPlacesInfo(concertId);

        if (places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> scheduleIdList = new ArrayList<>();
        List<LocalDateTime> scheduleDateList = new ArrayList<>();
        for (Place place : places) {
            List<Schedule> schedules = concertService.getSchedulesInfo(concertId, place.getId());
            for (Schedule schedule : schedules) {
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

        List<Seat> seats = concertService.getSeatsInfo(scheduleId);

        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> seatIds = new ArrayList<>();
        List<Long> seatPrices = new ArrayList<>();
        for (Seat seat : seats) {
            seatIds.add(seat.getId());
            seatPrices.add(seat.getPrice());
        }

        SeatsResponse response = new SeatsResponse();
        response.setIds(seatIds);
        response.setPrices(seatPrices);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/seats")
    public ResponseEntity<ReservationResponse> reserveSeat(@RequestBody ReservationRequest request) {
        if (request.getScheduleId() == null ||
                request.getSeatId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ReservationResponse response = reservationMapper.domainToResponse(concertService.makeReservation(request.getUserId(), request.getSeatId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cheat/concert")
    public ResponseEntity<ConcertCreateResponse> cheat_CreateConcertBatch(@RequestBody String requestBody) {
        try {
            Map<String, Integer> requestMap = objectMapper.readValue(requestBody, Map.class);
            Integer num = requestMap.get("number");

            List<Concert> concerts = concertService.createConcertBatch(num);

            List<Long> ids = new ArrayList<>();
            List<String> names = new ArrayList<>();
            for (Concert concert : concerts) {
                ids.add(concert.getId());
                names.add(concert.getName());
            }
            ConcertCreateResponse response = new ConcertCreateResponse();
            response.setIds(ids);
            response.setNames(names);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cheat/place")
    public ResponseEntity<PlaceCreateResponse> cheat_CreatePlaceBatch(@RequestBody String requestBody) {
        try {
            PlaceCreateResponse response = new PlaceCreateResponse();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}