package com.university.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    // Fixed: Changed from findByTeacherId to findByResponsibleTeacherId
    List<Module> findByResponsibleTeacherId(Long teacherId);
}