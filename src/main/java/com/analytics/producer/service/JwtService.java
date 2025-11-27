package com.analytics.producer.service;

import com.analytics.producer.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;
    private final RedisTemplate<String,String> redisTemplate;

    public Jws<Claims> parseJwt(String token) {
        return jwtUtil.parseToken(token);
    }

    public boolean validateToken(String appId,Integer tokenVersion)
    {
        Integer currVersion = Integer.valueOf(redisTemplate.opsForValue().get("app_token_version"+appId));

        if(tokenVersion==null || tokenVersion!=currVersion) return false;

        String status = redisTemplate.opsForValue().get("app_status"+appId);

        if(status==null || !status.equalsIgnoreCase("active")) return false;

        return true;

    }
}
