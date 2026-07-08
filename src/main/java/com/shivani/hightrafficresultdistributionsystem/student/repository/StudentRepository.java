package com.shivani.hightrafficresultdistributionsystem.student.repository;

import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByRollNumber(String roolNumber);

    List<Student> findByDeletedAtIsNull();

    Optional<Student> findByRollNumberAndDeletedAtIsNull(String rollNo);

    Optional<Student> findByIdAndDeletedAtIsNull(Long studentId);

    //Admins
    Optional<Student> findByRollNumber(String rollNo);

    List<Student> findByDeletedAtIsNotNull();

    Optional<Student> findByIdAndDeletedAtIsNotNull(Long studentId);
}
