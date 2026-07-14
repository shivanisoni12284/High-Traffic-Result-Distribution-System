package com.shivani.hightrafficresultdistributionsystem.marks.dto;

import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarksRequestDto {

    private Student student;

    private Subject subject;

    @Min(0)
    private Integer theoryMarks;

    @Min(0)
    private Integer practicalMarks;

//    private BigDecimal totalMarks;

//    @Enumerated(EnumType.STRING)
//    private Grade grade;
}
