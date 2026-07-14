package com.shivani.hightrafficresultdistributionsystem.auth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequest {

    @NotBlank(message = "Username is required")
    private String username;


    @NotBlank(message = "Password is required")
    private String password;
}
