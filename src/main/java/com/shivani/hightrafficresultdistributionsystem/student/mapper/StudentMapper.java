package com.shivani.hightrafficresultdistributionsystem.student.mapper;


import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentRequestDto;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentResponseDto;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentUpdateRequestDto;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import org.mapstruct.*;

import java.beans.BeanProperty;

@Mapper(componentModel= "spring")
public interface StudentMapper {


    Student toEntity(StudentRequestDto dto);

    StudentResponseDto toResponseDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Student updateStudentFromDto(StudentRequestDto dto, @MappingTarget Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Student partialUpdateStudentFromDto(StudentUpdateRequestDto requestDto,@MappingTarget Student student);
}
