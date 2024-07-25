package com.project.concert_reservation.common.jwt;

import com.project.concert_reservation.support.exception.CustomException;
import com.project.concert_reservation.support.jwt.JwtConfig;
import com.project.concert_reservation.support.jwt.JwtTokenValidator;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtTokenValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenValidatorTest.class);

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtTokenValidator jwtTokenValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecretKey secretKey = new SecretKeySpec(new byte[64], "HmacSHA256");
        when(jwtConfig.getSecretKey()).thenReturn(secretKey);
        logger.info("Setup complete with SecretKey");
    }

    @Test
    public void testValidateToken() {
        String token = Jwts.builder()
                .subject("user123")
                .signWith(jwtConfig.getSecretKey())
                .compact();

        assertTrue(jwtTokenValidator.validateToken(token), "Token should be valid");
        logger.info("Success: token is valid: {}", token);
    }

    @Test
    public void testValidateToken_invalid() {
        Exception exception = assertThrows(CustomException.class, () -> {
            jwtTokenValidator.validateToken("invalidToken");
        });
        logger.info("Success: expected exception for invalid token: {}", exception.getMessage());
    }

    @Test
    public void testGetUsername() {
        String token = Jwts.builder()
                .subject("user123")
                .signWith(jwtConfig.getSecretKey())
                .compact();

        String username = jwtTokenValidator.getUsername(token);
        assertEquals("user123", username, "Username should match");
        logger.info("Success: extracted username from token: {}", username);
    }

    @Test
    public void testResolveToken() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");

        String token = jwtTokenValidator.resolveToken(request);
        assertEquals("validToken", token, "Token should be resolved correctly");
        logger.info("Success: resolved token from request: {}", token);
    }

    @Test
    public void testResolveToken_null() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn(null);

        String token = jwtTokenValidator.resolveToken(request);
        assertNull(token, "Token should be null when Authorization header is missing or invalid");
        logger.info("Success: Token resolved to null due to missing or invalid Authorization header");
    }
}