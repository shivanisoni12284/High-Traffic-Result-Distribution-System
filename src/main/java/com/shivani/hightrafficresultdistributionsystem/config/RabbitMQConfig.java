package com.shivani.hightrafficresultdistributionsystem.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "pdf.queue";
    public static final String EXCHANGE_NAME = "pdf.exchange";
    public static final String PDF_ROUTING_KEY = "pdf.generate";

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
    public Binding binding(Queue pdfQueue,DirectExchange pdfExchange){

        return BindingBuilder
                .bind(pdfQueue)
                .to(pdfExchange)
                .with(PDF_ROUTING_KEY);

    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;

    }


}
