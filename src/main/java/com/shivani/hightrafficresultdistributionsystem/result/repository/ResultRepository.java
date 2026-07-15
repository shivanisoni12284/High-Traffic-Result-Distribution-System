package com.shivani.hightrafficresultdistributionsystem.result.repository;

import com.shivani.hightrafficresultdistributionsystem.result.dto.ResultResponseDto;
import com.shivani.hightrafficresultdistributionsystem.result.schema.Result;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {
    Result findByStudent(Student student);

    boolean existsByStudent(Student student);

//    ResultResponseDto findByRollNumber(String rollNumber);
}
