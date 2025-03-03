package com.project.concert_reservation.support.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final JwtConfig jwtConfig;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Long userId, String payload) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtConfig.getValidityInMilliseconds());

        return Jwts.builder()
                .header()
                .keyId(String.valueOf(userId))
                .and()
                .issuedAt(now)
                .expiration(validity)
                .subject(payload)
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

}