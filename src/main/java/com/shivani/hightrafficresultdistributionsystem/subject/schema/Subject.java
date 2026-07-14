package com.shivani.hightrafficresultdistributionsystem.subject.schema;

import com.shivani.hightrafficresultdistributionsystem.common.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Subject extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String subjectCode;

    @Column(nullable = false)
    private String subjectName;

    private Integer totalTheoryMarks;

    private Integer totalPracticalMarks;

    private Integer passingMarks;

    @Builder.Default
    private Boolean active = true;



}
