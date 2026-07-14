package com.shivani.hightrafficresultdistributionsystem.ratelimit.service;

import com.shivani.hightrafficresultdistributionsystem.common.exception.RateLimitExceededException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
//@Slf4j
public class RateLimiterService {

    private final StringRedisTemplate stringRedisTemplate;

    @Value("${rate-limit.max-requests}")
    private int MAX_REQUESTS;

    @Value("${rate-limit.window-seconds}")
    private Duration WINDOW ;

    public void checkRateLimit(Long studentId,String endpoint){

        String key = "rateLlimit:student:" + studentId + ":"+endpoint;

        String currentCount = stringRedisTemplate.opsForValue().get(key);

        if(currentCount == null){
            stringRedisTemplate.opsForValue().set(key,"1",WINDOW);
            return;
        }

        int count = Integer.parseInt(currentCount);
        System.out.println(count);


        if(count >= MAX_REQUESTS){
            throw new RateLimitExceededException("Too many requests. Please try again later.");
        }

        stringRedisTemplate.opsForValue().increment(key);

    }
}
