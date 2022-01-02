package com.fei.api.security;

import com.fei.api.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    @Value("${secret.key}")
    private String SECRET_KEY;

    public String create(User user) {
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setSubject(user.getUserId())
                .setIssuer("fei")
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

        return claims.getSubject();
    }
}
