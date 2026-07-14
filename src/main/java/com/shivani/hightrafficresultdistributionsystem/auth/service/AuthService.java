package com.shivani.hightrafficresultdistributionsystem.auth.service;

import com.shivani.hightrafficresultdistributionsystem.auth.dto.LoginRequest;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.LoginResponse;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.StudentRegisterRequest;
import com.shivani.hightrafficresultdistributionsystem.auth.dto.StudentRegisterResponse;
import com.shivani.hightrafficresultdistributionsystem.auth.mapper.AuthMapper;
import com.shivani.hightrafficresultdistributionsystem.auth.token.JwtUtil;
import com.shivani.hightrafficresultdistributionsystem.common.exception.StudentAlreadyRegisteredException;
import com.shivani.hightrafficresultdistributionsystem.student.dto.StudentResponseDto;

import com.shivani.hightrafficresultdistributionsystem.student.service.StudentService;
import com.shivani.hightrafficresultdistributionsystem.user.repository.UserRepository;
import com.shivani.hightrafficresultdistributionsystem.user.schema.Role;
import com.shivani.hightrafficresultdistributionsystem.user.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final StudentService studentService;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;



    // register student
    public StudentRegisterResponse registerStudent(StudentRegisterRequest studentRequest){

        // Check whether the student exists
        StudentResponseDto student = studentService.getStudentByRollNumber(studentRequest.getRollNumber());  // whether student with rollNumber present or not

        // Check whether already registered
        if(userRepository.existsByUsername(studentRequest.getRollNumber())){   // student with rollNumber is registered
            throw  new StudentAlreadyRegisteredException("student is already registered.");
        }

        User user = User.builder()
                .username(student.getRollNumber())
                .role(Role.STUDENT)
                .password(passwordEncoder.encode(studentRequest.getPassword()))
                .build();

        userRepository.save(user);

        return authMapper.toResponse(user);
    }

    //login   -> this is for all
    public LoginResponse login(LoginRequest loginRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    // register admin
//    public AdminRegisterResponse registerAdmin(StudentRegisterRequest request){
//
//        // Check whether the student exists
//        StudentResponseDto student = studentService.getStudentByRollNumber(request.getRollNumber());  // whether student with rollNumber present or not
//
//        // Check whether already registered
//        if(userRepository.existsByRollNumber(request.getRollNumber())){   // student with rollNumber is registered
//            throw  new StudentAlreadyRegisteredException("student is already registered.");
//        }
//
//        User user = User.builder()
//                .username(student.getRollNumber())
//                .role(Role.STUDENT)
//                .password(passwordEncoder.encode(request.getPassword()))
//                .build();
//
//        userRepository.save(user);
//
//        return authMapper.toResponse(user);
//    }



}
