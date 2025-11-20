package com.analytics.producer.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {


    private final Key key;

    public JwtUtil(@Value("${secret.key}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Jws<Claims> parseToken(String token){
    return  Jwts.parserBuilder().setSigningKey(key)
                    .build().parseClaimsJws(token);
    }

}
