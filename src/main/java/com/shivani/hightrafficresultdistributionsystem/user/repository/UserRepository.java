package com.shivani.hightrafficresultdistributionsystem.user.repository;

import com.shivani.hightrafficresultdistributionsystem.user.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByRollNumber(String rollNumber);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String rollNumber);
}
