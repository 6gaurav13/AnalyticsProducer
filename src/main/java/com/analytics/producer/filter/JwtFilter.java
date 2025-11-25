package com.analytics.producer.filter;


import com.analytics.producer.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                String jwt = token.substring(7);
                Jws<Claims> claims = jwtService.parseJwt(jwt);
                String appId = claims.getBody().get("appId", String.class);
                request.setAttribute("appId", appId); //for later use so we don;t need to send appId in request it is directly from jwt token
            return;
            }
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
