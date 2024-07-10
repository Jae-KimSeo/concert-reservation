package com.project.concert_reservation.common.jwt;

import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtConfig jwtConfig;
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String createToken(String userId, String payload) {
        String jwt = Jwts.builder()

                .header()
                .keyId(userId)
                .and()

                .subject(payload)
                .signWith(key)

                .compact();
        return jwt;
    }

}