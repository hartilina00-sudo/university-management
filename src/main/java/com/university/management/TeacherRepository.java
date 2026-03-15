package com.university.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Used to link the logged-in email to the Teacher entity
    Teacher findByEmail(String email);
}