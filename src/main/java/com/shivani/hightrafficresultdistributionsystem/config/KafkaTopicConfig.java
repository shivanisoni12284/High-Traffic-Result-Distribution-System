package com.shivani.hightrafficresultdistributionsystem.config;

import com.shivani.hightrafficresultdistributionsystem.kafka.properties.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private final KafkaTopicProperties kafkaTopicProperties;


    public NewTopic resultGenerationTopic(){
        return TopicBuilder
                .name(kafkaTopicProperties.getResultGeneration())
                .partitions(6)
                .replicas(1)
                .build();

    }
}
