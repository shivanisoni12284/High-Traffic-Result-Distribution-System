package com.shivani.hightrafficresultdistributionsystem.auth.controller;

import com.shivani.hightrafficresultdistributionsystem.auth.dto.LoginRequest;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.LoginResponse;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.StudentRegisterRequest;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.StudentRegisterResponse;
import com.shivani.hightrafficresultdistributionsystem.auth.service.AuthService;
import com.shivani.hightrafficresultdistributionsystem.common.util.ApiResponse;
import com.shivani.hightrafficresultdistributionsystem.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Validator;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/registerStudent")
    public ResponseEntity<ApiResponse<StudentRegisterResponse>> registerStudent( @Valid @RequestBody StudentRegisterRequest studentRegisterRequest){
        StudentRegisterResponse registeredStudent = authService.registerStudent(studentRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("student registered successfully. ",registeredStudent));
    }

    @PostMapping("/loginUser")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse loginUser = authService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("user login successfully. ",loginUser));
    }
}
