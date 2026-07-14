package com.shivani.hightrafficresultdistributionsystem.marks.mapper;

import com.shivani.hightrafficresultdistributionsystem.marks.dto.MarksRequestDto;
import com.shivani.hightrafficresultdistributionsystem.marks.dto.MarksResponseDto;
import com.shivani.hightrafficresultdistributionsystem.marks.schema.Marks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarksMapper {

    Marks toEntity(MarksRequestDto marksRequestDto);

    MarksResponseDto toResponseDto(Marks marks);
}
