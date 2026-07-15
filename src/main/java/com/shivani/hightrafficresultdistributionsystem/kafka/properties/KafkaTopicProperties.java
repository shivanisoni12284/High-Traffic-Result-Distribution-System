package com.shivani.hightrafficresultdistributionsystem.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.kafka.topic")
public class KafkaTopicProperties {

    private String resultGeneration;
}
