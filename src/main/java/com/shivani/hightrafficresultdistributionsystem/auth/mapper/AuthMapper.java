package com.shivani.hightrafficresultdistributionsystem.auth.mapper;

import com.shivani.hightrafficresultdistributionsystem.auth.dto.StudentRegisterRequest;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.StudentRegisterResponse;
import com.shivani.hightrafficresultdistributionsystem.user.schema.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User toEntity(StudentRegisterRequest dto);

    StudentRegisterResponse toResponse(User user);


}
