package com.shivani.hightrafficresultdistributionsystem.marks.dto;

import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarksResponseDto {

    private Long id;

    private Student student;

    private Subject subject;

    private BigDecimal theoryMarks;

    private BigDecimal practicalMarks;

    private BigDecimal totalMarks;

    private Grade grade;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
