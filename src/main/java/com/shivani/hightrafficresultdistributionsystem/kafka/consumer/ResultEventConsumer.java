package com.shivani.hightrafficresultdistributionsystem.kafka.consumer;

import com.shivani.hightrafficresultdistributionsystem.common.exception.ResultAlreadyGeneratedException;
import com.shivani.hightrafficresultdistributionsystem.kafka.event.ResultGenerationEvent;
//import com.shivani.hightrafficresultdistributionsystem.rabbitmq.event.PdfGenerationEvent;
//import com.shivani.hightrafficresultdistributionsystem.rabbitmq.event.SubjectPdfData;
//import com.shivani.hightrafficresultdistributionsystem.rabbitmq.producer.PdfEventProducer;
import com.shivani.hightrafficresultdistributionsystem.result.dto.ResultResponseDto;
import com.shivani.hightrafficresultdistributionsystem.result.dto.SubjectResultDto;
import com.shivani.hightrafficresultdistributionsystem.result.schema.Result;
import com.shivani.hightrafficresultdistributionsystem.result.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultEventConsumer {

    private final ResultService resultService;
//    private final PdfEventProducer pdfEventProducer;

    @KafkaListener(
            topics = "${app.kafka.topic.result-generation}",
            groupId = "result-service"

    )

    public void consume(ResultGenerationEvent event) {

        log.info("Result generated for {}", event.getRollNumber());

        ResultResponseDto result = resultService.generateResult(event.getRollNumber());
        if (result == null) {
            log.info("Skipping duplicate event for {}", event.getRollNumber());
            return; // Kafka considers the message successfully processed
        }


        log.info("Result generated successfully");


//        log.info("Received Event : {}",event);
//        System.out.println("===================");
//        System.out.println("KAFKA CONSUMER RECEIVED EVENT");
//        System.out.println(event);
//        System.out.println("===================");
//
//        log.info(
//                "Thread {} processed {}",
//                Thread.currentThread().getName(),
//                event
//        );


    }
}
