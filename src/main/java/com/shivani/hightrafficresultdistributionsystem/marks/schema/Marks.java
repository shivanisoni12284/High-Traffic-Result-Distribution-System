package com.shivani.hightrafficresultdistributionsystem.marks.schema;

import com.shivani.hightrafficresultdistributionsystem.common.util.BaseEntity;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;

@Entity
@Table(name = "marks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marks extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Min(0)
    private Integer theoryMarks;

    @Min(0)
    private Integer practicalMarks;

    private Integer totalMarks;

    @Enumerated(EnumType.STRING)
    private Grade grade;


}
