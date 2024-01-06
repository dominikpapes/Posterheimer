package com.rest.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;
@Component
public class JwtUtil {
    private final String secret = "0tt0RZKDc6o3uNQp5q9JOA2T_A60mye3wA9v4jklLARgtLfuD9VsjgsgWDr8Ltx-MH0Biq-2GDbD-T5I-_5alw";
    private final long expirationTime = 86400000;

    public String generateToken(Authentication authentication) {
        return JWT.create()
                .withSubject(authentication.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withClaim("roles", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }


}
