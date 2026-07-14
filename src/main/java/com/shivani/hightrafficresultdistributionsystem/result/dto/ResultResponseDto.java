package com.shivani.hightrafficresultdistributionsystem.result.dto;

import com.shivani.hightrafficresultdistributionsystem.result.schema.ResultType;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDto {

    private String rollNumber;

    private String studentName;

    private String motherName;

    private String fatherName;

    private LocalDate dateOfBirth;

    private String schoolName;

    private List<SubjectResultDto> subjects;

    private ResultType result;


}
