package com.shivani.hightrafficresultdistributionsystem.result.mapper;


import com.shivani.hightrafficresultdistributionsystem.marks.schema.Marks;
import com.shivani.hightrafficresultdistributionsystem.result.dto.ResultResponseDto;
import com.shivani.hightrafficresultdistributionsystem.result.dto.SubjectResultDto;
import com.shivani.hightrafficresultdistributionsystem.result.schema.Result;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResultMapper {

    @Mapping(source = "student.rollNumber", target = "rollNumber")
    @Mapping(source = "student.studentName", target = "studentName")
    @Mapping(source = "student.fatherName", target = "fatherName")
    @Mapping(source = "student.motherName", target = "motherName")
    @Mapping(source = "student.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "student.schoolName", target = "schoolName")
    @Mapping(source = "result", target = "result")
    ResultResponseDto toResponseDto(Result student);


    @Mapping(source = "subject.subjectCode", target = "subjectCode")
    @Mapping(source = "subject.subjectName", target = "subjectName")
    SubjectResultDto toSubjectResultDto(Marks marks);
}
