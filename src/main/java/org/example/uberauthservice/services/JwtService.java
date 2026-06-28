package org.example.uberauthservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiry}")
    private int expiry;

    public String generateToken(Map<String, Object>payload, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000);
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(username)
                .expiration(expiryDate)
                .signWith(key)
                .compact();

    }
}
