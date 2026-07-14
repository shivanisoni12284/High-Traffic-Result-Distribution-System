package com.shivani.hightrafficresultdistributionsystem.result.dto;

import com.shivani.hightrafficresultdistributionsystem.marks.schema.Grade;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResultDto {

    private String subjectCode;

    private String subjectName;

    private Integer theoryMarks;

    private Integer practicalMarks;

    private Integer totalMarks;

    private Grade grade;


}
