package com.project.concert_reservation.interfaces.point.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.concert_reservation.application.point.service.PointService;
import com.project.concert_reservation.domain.concert.dto.ConcertCreateResponse;
import com.project.concert_reservation.domain.concert.model.Concert;
import com.project.concert_reservation.domain.point.model.Point;
import com.project.concert_reservation.interfaces.point.controller.dto.PointCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public Long getPoint(@RequestBody Long userId){
        // TODO : Get userId from jwt
        return pointService.getPoint(userId).getPoint();
    }

    @PostMapping("/recharge")
    public Long rechargePoint(@RequestBody Long userId, @RequestBody Long point){
        // TODO : Get userId from jwt
        return pointService.rechargePoint(userId, point).getPoint();
    }

    @PostMapping("/spend")
    public Long spendPoint(@RequestBody Long userId, @RequestBody Long point){
        // TODO : Get userId from jwt
        return pointService.spendPoint(userId, point).getPoint();
    }

    @PostMapping("/cheat/point")
    public ResponseEntity<PointCreateResponse> cheat_CreatePointBatch(@RequestBody String requestBody) {
        try {
            Map<String, Integer> requestMap = objectMapper.readValue(requestBody, Map.class);
            Integer num = requestMap.get("number");

            List<Point> points = pointService.createPointBatch(num);

            List<Long> ids = new ArrayList<>();
            List<Long> userIds = new ArrayList<>();
            for (Point point : points){
                ids.add(point.getId());
                userIds.add(point.getUserId());
            }
            PointCreateResponse response = new PointCreateResponse();
            response.setIds(ids);
            response.setUserIds(userIds);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
