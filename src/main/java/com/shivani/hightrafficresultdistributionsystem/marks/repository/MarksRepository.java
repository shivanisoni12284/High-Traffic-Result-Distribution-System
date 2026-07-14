package com.shivani.hightrafficresultdistributionsystem.marks.repository;

import com.shivani.hightrafficresultdistributionsystem.marks.schema.Marks;
import com.shivani.hightrafficresultdistributionsystem.student.schema.Student;
import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Long> {


    boolean existsByStudentAndSubject(Student student, Subject subject);

    List<Marks> findByStudent(Student student);
}
