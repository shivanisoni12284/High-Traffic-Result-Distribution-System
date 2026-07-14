package com.shivani.hightrafficresultdistributionsystem.subject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectUpdateRequestDto {

    private String subjectCode;

    private String subjectName;

    private Integer totalTheoryMarks;

    private Integer totalPracticalMarks;

    private Integer passingMarks;


}
