package com.project.concert_reservation.interfaces.concert.controller;


import com.project.concert_reservation.interfaces.concert.controller.dto.ReservationRequest;
import com.project.concert_reservation.interfaces.concert.controller.dto.ReservationSeatsResponse;
import com.project.concert_reservation.domain.concert.domain.ConcertDomain;
import com.project.concert_reservation.domain.concert.domain.PlaceDomain;
import com.project.concert_reservation.domain.concert.domain.ScheduleDomain;
import com.project.concert_reservation.application.concert.service.ReservationService;
import com.project.concert_reservation.infra.concert.repository.ReservationRepository;
import com.project.concert_reservation.interfaces.concert.controller.dto.ReservationDatesResponse;
import com.project.concert_reservation.interfaces.concert.controller.dto.ReservationResponse;
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
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @GetMapping("/dates/{concertId}")
    public ResponseEntity<ReservationDatesResponse> getAvailableDates(@PathVariable Long concertId) {
        if (concertId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ConcertDomain concertDomain = reservationService.getConcertInfo(concertId);
        List<PlaceDomain> placeDomains = reservationService.getPlaceInfo(concertId);

        List<Long> scheduleIdList = new ArrayList<>();
        List<LocalDateTime> scheduleDateList = new ArrayList<>();
        for (PlaceDomain placeDomain : placeDomains){
            List<ScheduleDomain> scheduleDomains = reservationService.getScheduleInfo(concertId, placeDomain.getId());
            for (ScheduleDomain scheduleDomain : scheduleDomains){
                scheduleIdList.add(scheduleDomain.getId());
                scheduleDateList.add(scheduleDomain.getEventDate());
            }
        }

        ReservationDatesResponse response = new ReservationDatesResponse();
        response.setScheduleIds(scheduleIdList);
        response.setScheduleDates(scheduleDateList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/seats/{scheduleId}")
    public ResponseEntity<ReservationSeatsResponse> getAvailableSeats(@RequestHeader("Authorization") String authorization,
                                                                      @PathVariable String scheduleId) {
        if (scheduleId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (authorization.equals("Bearer invalid-token")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ReservationSeatsResponse response = new ReservationSeatsResponse();
        response.setSeatIds(Arrays.asList("seat1", "seat2", "seat3"));
        response.setSeatNos(Arrays.asList("A1", "A2", "A3"));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/seats")
    public ResponseEntity<ReservationResponse> reserveSeat(@RequestHeader("Authorization") String authorization,
                                                           @RequestBody ReservationRequest request) {
        if (request.getScheduleId()  == null ||
        request.getSeatId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ReservationResponse response = reservationMapper.domainToResponse(reservationService.makeReservation(request.getSeatId()));
        response.setReserved(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}