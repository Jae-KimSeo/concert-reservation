package com.project.concert_reservation.reservation_service.Presentation.controller;


import com.project.concert_reservation.reservation_service.Presentation.controller.dto.ReservationDatesResponse;
import com.project.concert_reservation.reservation_service.Presentation.controller.dto.ReservationRequest;
import com.project.concert_reservation.reservation_service.Presentation.controller.dto.ReservationResponse;
import com.project.concert_reservation.reservation_service.Presentation.controller.dto.ReservationSeatsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @GetMapping("/dates/{concertId}")
    public ResponseEntity<ReservationDatesResponse> getAvailableDates(@RequestHeader("Authorization") String authorization,
                                                                      @PathVariable String concertId) {
        if (concertId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (authorization.equals("Bearer invalid-token")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ReservationDatesResponse response = new ReservationDatesResponse();
        response.setScheduleIds(Arrays.asList("IU-Concert", "Eminem-Concert", "KimJaeHyun-Concert"));
        response.setScheduleDates(Arrays.asList("2024-07-03", "2024-07-04", "2024-07-05"));

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
        if (request.getScheduleId().isEmpty() ||
                request.getSeatId() == null || request.getSeatId().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!authorization.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (authorization.equals("Bearer invalid-token")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ReservationResponse response = new ReservationResponse();
        response.setReserved(true);
        response.setReservedAt(LocalDateTime.now());
        response.setHoldDeadline(LocalDateTime.now().plusMinutes(15));
        response.setSeatNo("A1");
        response.setPrice(5000);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}