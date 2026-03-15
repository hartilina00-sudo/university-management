package com.university.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    public void saveSession(Session session) throws Exception {
        // 1. Check for conflicts (Only for NEW sessions)
        if (session.getId() == null) {

            // We need the Teacher's ID to check if he is busy
            Long teacherId = session.getModule().getResponsibleTeacher().getId();

            List<Session> conflicts = sessionRepository.findConflicts(
                    session.getRoom(),
                    teacherId,
                    session.getGroupName(),
                    session.getDayOfWeek(),
                    session.getStartTime(),
                    session.getEndTime()
            );

            if (!conflicts.isEmpty()) {
                throw new Exception("Conflit détecté ! La Salle, le Professeur ou le Groupe est déjà occupé à ce créneau.");
            }
        }
        // 2. Save if clear
        sessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}