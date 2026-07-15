package com.shivani.hightrafficresultdistributionsystem.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "pdf.quue";
    public static final String EXCHANGE_NAME = "pdf.exchange";
    public static final String ROUTING_KEY = "pdf.generate";

    @Bean
    public Queue pdfQueue(){
        return new Queue(
            QUEUE_NAME,true
        );

    }

    @Bean
    public DirectExchange pdfExchange(){
        return new DirectExchange(
                EXCHANGE_NAME
        );

    }

    @Bean
    public Binding binding(){

        return BindingBuilder
                .bind(pdfQueue())
                .to(pdfExchange())
                .with(ROUTING_KEY);

    }


}
