package com.shivani.hightrafficresultdistributionsystem.auth.dto;

import com.shivani.hightrafficresultdistributionsystem.user.schema.Role;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentRegisterResponse {


    private Long id;

    private String username;

    private String password;

    private Role role;

    private Boolean enabled;


}
