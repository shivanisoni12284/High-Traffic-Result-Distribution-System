package com.shivani.hightrafficresultdistributionsystem.kafka.producer;

import com.shivani.hightrafficresultdistributionsystem.config.KafkaProducerConfig;
import com.shivani.hightrafficresultdistributionsystem.kafka.event.ResultGenerationEvent;
import com.shivani.hightrafficresultdistributionsystem.kafka.properties.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultEventProducer {

    private final KafkaTemplate<String, ResultGenerationEvent> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    public void publish(ResultGenerationEvent event){
        CompletableFuture<SendResult<String,ResultGenerationEvent>> future = kafkaTemplate.send(
                kafkaTopicProperties.getResultGeneration(),
                event.getRollNumber(),
                event

        );

        future.whenComplete((result,exception) ->{
            if(exception != null){
                log.error("Failed to publish event {}",event.getEventId(),exception);
                return;
            }
            log.info(
                    "Event {} published to partition {} offset {}",
                    event.getEventId(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset()
            );
        });

    }
}
