package com.analytics.producer.service;

import com.analytics.producer.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Jws<Claims> parseJwt(String token) {
        return jwtUtil.parseToken(token);
    }
}
