package com.analytics.producer.service;

import com.analytics.producer.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public Jws<Claims> parseJwt(String token) {
        return jwtUtil.parseToken(token);
    }
}
