package com.shivani.hightrafficresultdistributionsystem.subject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponseDto {

    private Long id;

    private String subjectCode;

    private String subjectName;

    private Integer totalTheoryMarks;

    private Integer totalPracticalMarks;

    private Integer passingMarks;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
