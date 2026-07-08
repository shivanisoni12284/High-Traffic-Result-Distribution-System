package com.shivani.hightrafficresultdistributionsystem.student.dto;


import com.shivani.hightrafficresultdistributionsystem.student.schema.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @NotBlank(message = "Student name is required")
    @Size(max = 100,message = " Student name cannot exceed 100 characters")
    private String studentName;

    @NotBlank(message = "Address is required")
    private String address;

    private String fatherName;

    private String motherName;

    @NotBlank(message = "Guardian name is required")
    private String guardian;

    @NotBlank(message = "school name is required")
    @Size(max = 200 ,message = "Student name cannot exceed 200 characters")
    private String schoolName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Pattern(regexp = "^[6-9]\\d{9}$",message = " phone number must contain only 10 digits and start with 6, 7, 8, or 9")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message  = "Invalid email address")
    private String email;   // password toh check karne ke liye hoga

    @NotNull(message = "Date of birth is required")
    @Past(message =" Date os birth must be in the past")
//    max age annotation
    private LocalDate dateOfBirth;

    @NotNull(message = "Admission year is required")
    @Min(value = 2000, message = " Admission year must be after 2000")
    @Max(value = 2100, message = "Invalid admission year")
    private Integer admissionYear;

    private Boolean active;


}
