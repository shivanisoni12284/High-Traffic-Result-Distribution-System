package com.shivani.hightrafficresultdistributionsystem.auth.dto;

import com.shivani.hightrafficresultdistributionsystem.user.schema.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginResponse {

    private String token;

    private String username;

    private Role role;


}
