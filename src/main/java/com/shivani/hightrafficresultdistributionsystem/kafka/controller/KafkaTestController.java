package com.shivani.hightrafficresultdistributionsystem.kafka.controller;

import com.shivani.hightrafficresultdistributionsystem.kafka.event.ResultGenerationEvent;
import com.shivani.hightrafficresultdistributionsystem.kafka.producer.ResultEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RequestMapping("/kafka")
@RestController
@RequiredArgsConstructor
public class KafkaTestController {

    private final ResultEventProducer producer;

    @PostMapping("/{rollNumber}")
    public String send(@PathVariable String rollNumber){
        ResultGenerationEvent event = ResultGenerationEvent.builder()
                .eventId(UUID.randomUUID())
                .rollNumber(rollNumber)
                .createdAt(LocalDateTime.now())
                .build();

        producer.publish(event);

        return "Published Successfully";
    }

}
