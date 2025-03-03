package com.project.concert_reservation.common.jwt;

import com.project.concert_reservation.exception.CustomException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator {
    private final JwtConfig jwtConfig;

    public JwtTokenValidator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    public boolean validateToken(String token) {
        //TODO : validate expiration
        try {
            Jwts.parser().
                    verifyWith(jwtConfig.getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().
                verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
