package com.university.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Fixes the red error in EvaluationController
    List<Evaluation> findByStudentEmail(String email);

    // Fixed: Matches the nested path: Evaluation -> Module -> responsibleTeacher
    List<Evaluation> findByModuleResponsibleTeacherId(Long teacherId);
}