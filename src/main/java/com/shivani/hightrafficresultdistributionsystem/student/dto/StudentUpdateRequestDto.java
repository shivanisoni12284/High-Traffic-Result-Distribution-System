package com.shivani.hightrafficresultdistributionsystem.student.dto;

import com.shivani.hightrafficresultdistributionsystem.student.schema.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequestDto {
    private String rollNumber;

    private String studentName;

    private String address;

    private String fatherName;

    private String motherName;

    private String schoolName;

    private Gender gender;

    private String phoneNumber;

    private String email;   // password toh check karne ke liye hoga

    private LocalDate dateOfBirth;

    private Integer admissionYear;

    private Boolean active;

}
