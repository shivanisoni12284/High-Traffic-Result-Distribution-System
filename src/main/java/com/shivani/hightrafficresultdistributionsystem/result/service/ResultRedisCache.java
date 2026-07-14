package com.shivani.hightrafficresultdistributionsystem.result.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivani.hightrafficresultdistributionsystem.result.dto.ResultResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultRedisCache {
    private static final String KEY_RESULT = "result";
    private static final Duration CACHE_TTL  = Duration.ofMinutes(1);

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public Optional<ResultResponseDto> getResult(String rollNumber){
        String responseJson = stringRedisTemplate.opsForValue().get(KEY_RESULT + rollNumber);

        if(responseJson == null){
            log.info("Cache miss for result:{}",rollNumber);
            return Optional.empty();
        }

        //cache hit
        log.info("Cache hit for result: {}",rollNumber);

        try{
            ResultResponseDto resultResponseDto = objectMapper.readValue(responseJson, ResultResponseDto.class);
            return Optional.of(resultResponseDto);
        }catch (Exception e){
            log.error("Error parsing result from cache:{}",e.getMessage());
            stringRedisTemplate.delete(KEY_RESULT + rollNumber);// because the data is corrupted
            return Optional.empty();
        }
    }

    public void putResult(String rollNumber,ResultResponseDto resultResponseDto){
        try{
            stringRedisTemplate.opsForValue().set(
                    KEY_RESULT+ rollNumber,
                    objectMapper.writeValueAsString(resultResponseDto),
                    CACHE_TTL
            );
        }catch(Exception e){
            throw new RuntimeException("error serializing result to cache: "+e.getMessage());
        }
    }
}
