package com.shivani.hightrafficresultdistributionsystem.user.mapper;

import com.shivani.hightrafficresultdistributionsystem.user.dto.UserResponseDto;
import com.shivani.hightrafficresultdistributionsystem.user.schema.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

}
