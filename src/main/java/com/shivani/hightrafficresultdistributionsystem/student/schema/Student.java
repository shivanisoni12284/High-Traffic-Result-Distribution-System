package com.shivani.hightrafficresultdistributionsystem.student.schema;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String rollNumber;

    @Column(nullable = false,length = 100)
    private String studentName;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 100)
    private String fatherName;

    @Column(length = 100)
    private String motherName;

    @Column(length = 100)
    @NotBlank
    private String guardian;

    @Column(nullable = false,length = 200)
    private String schoolName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Column(length = 10)
    private String phoneNumber;

    @Column(nullable = false,unique = true)
    private String email;

    private LocalDate dateOfBirth;

    private Integer admissionYear;

    @Builder.Default
    private Boolean active=true;

    private LocalDateTime deletedAt;

    @CreatedDate
//    @Column(Updatable = "false")
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
