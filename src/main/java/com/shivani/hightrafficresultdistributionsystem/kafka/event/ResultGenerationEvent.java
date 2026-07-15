package com.shivani.hightrafficresultdistributionsystem.kafka.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultGenerationEvent {

    private UUID eventId;

    private String rollNumber;

    private LocalDateTime createdAt;


}
