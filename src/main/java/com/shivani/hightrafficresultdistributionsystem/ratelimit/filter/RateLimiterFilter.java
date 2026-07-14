package com.shivani.hightrafficresultdistributionsystem.ratelimit.filter;

import com.shivani.hightrafficresultdistributionsystem.auth.dto.CustomUserDetails;
import com.shivani.hightrafficresultdistributionsystem.common.exception.RateLimitExceededException;
import com.shivani.hightrafficresultdistributionsystem.ratelimit.service.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RateLimiterFilter extends OncePerRequestFilter {

    private final RateLimiterService rateLimiterService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/api/v1/auth")){

            filterChain.doFilter(request, response);
            return;
        }

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication != null && authentication.getPrincipal() instanceof CustomUserDetails){

                CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

                Long userId = user.getUserId();

                String endpoint = request.getRequestURI();

                rateLimiterService.checkRateLimit(userId,endpoint);

            }
            filterChain.doFilter(request,response);

        }catch(RateLimitExceededException e){
            response.setStatus(429);
            response.getWriter().write(e.getMessage());
        }


    }
}
