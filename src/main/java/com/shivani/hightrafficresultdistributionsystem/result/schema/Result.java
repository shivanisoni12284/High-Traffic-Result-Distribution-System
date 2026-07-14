package com.shivani.hightrafficresultdistributionsystem.result.schema;


import com.shivani.hightrafficresultdistributionsystem.common.util.BaseEntity;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Result extends BaseEntity {

    // student details
    @OneToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @Enumerated(EnumType.STRING)
    private ResultType result;

//    private Boolean published;

}
