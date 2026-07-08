package com.shivani.hightrafficresultdistributionsystem.student.dto;


import com.shivani.hightrafficresultdistributionsystem.student.schema.Gender;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponseDto {

    private Long id;

    private String rollNumber;

    private String studentName;

    private String address;

    private String fatherName;

    private String motherName;

    private String guardian;

    private String schoolName;

    private Gender gender;

    private String phoneNumber;

    private String email;   // password toh check karne ke liye hoga

    private LocalDate dateOfBirth;

    private Integer admissionYear;

    private Boolean active;

    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
