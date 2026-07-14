package com.shivani.hightrafficresultdistributionsystem.subject.mapper;

import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectRequestDto;
import com.shivani.hightrafficresultdistributionsystem.subject.dto.SubjectResponseDto;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" )
public interface SubjectMapper {

    Subject toEntity(SubjectRequestDto subjectRequestDto);


    SubjectResponseDto toResponseDto(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateEntity(SubjectRequestDto subjectRequestDto, @MappingTarget Subject subject);
}
