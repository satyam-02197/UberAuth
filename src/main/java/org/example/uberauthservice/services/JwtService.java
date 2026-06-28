package org.example.uberauthservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiry}")
    private int expiry;

    public String generateToken(Map<String, Object>payload, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000);
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(email)
                .expiration(expiryDate)
                .signWith(getSignKey())
                .compact();

    }

    private Claims extractAllPayloads(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllPayloads(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private Boolean validateToken(String token, String email) {
        final String userEmailFetchedFromToken = extractEmail(token);
        return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
    }

    private Object extractPayload(String token, String payloadKey) {
        Claims claim = extractAllPayloads(token);
        return (Object) claim.get(payloadKey);
    }

}
