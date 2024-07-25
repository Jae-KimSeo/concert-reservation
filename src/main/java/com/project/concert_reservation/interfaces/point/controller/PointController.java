package com.project.concert_reservation.interfaces.point.controller;

import com.project.concert_reservation.application.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
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
}
