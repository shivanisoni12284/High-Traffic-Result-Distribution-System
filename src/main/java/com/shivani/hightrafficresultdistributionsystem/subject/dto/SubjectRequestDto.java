package com.shivani.hightrafficresultdistributionsystem.subject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectRequestDto {

//    @NotBlank(message = " subjectCode is required")
    private String subjectCode;

    private String subjectName;

    private Integer totalTheoryMarks;

    private Integer totalPracticalMarks;

    private Integer passingMarks;


}
