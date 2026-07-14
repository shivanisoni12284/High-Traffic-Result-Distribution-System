package com.shivani.hightrafficresultdistributionsystem.user.dto;


import com.shivani.hightrafficresultdistributionsystem.user.schema.Role;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String password;

    private Role role;

    private Boolean enabled;
}
