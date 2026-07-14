package com.shivani.hightrafficresultdistributionsystem.subject.repository;

import com.shivani.hightrafficresultdistributionsystem.subject.schema.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    boolean existsBySubjectCode(String subjectCode);

    Optional<Subject> findByIdAndDeletedAtIsNull(Long id);
}
