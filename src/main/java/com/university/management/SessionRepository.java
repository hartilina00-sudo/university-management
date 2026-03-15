package com.university.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s FROM Session s WHERE " +
            "(s.room = :room OR s.module.responsibleTeacher.id = :teacherId OR s.groupName = :groupName) " +
            "AND s.dayOfWeek = :day " +
            "AND ((:start BETWEEN s.startTime AND s.endTime) OR (:end BETWEEN s.startTime AND s.endTime))")
    List<Session> findConflicts(@Param("room") String room,
                                @Param("teacherId") Long teacherId,
                                @Param("groupName") String groupName,
                                @Param("day") String day,
                                @Param("start") String start,
                                @Param("end") String end);

    List<Session> findByGroupName(String groupName);

    // Fixed: Matches the nested path: Session -> Module -> responsibleTeacher
    List<Session> findByModuleResponsibleTeacherId(Long teacherId);
}