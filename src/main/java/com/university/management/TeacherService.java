package com.university.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() { return teacherRepository.findAll(); }
    public void saveTeacher(Teacher t) { teacherRepository.save(t); }
    public void deleteTeacher(Long id) { teacherRepository.deleteById(id); }

    // ✅ NEW METHOD
    public Teacher getTeacherById(Long id) { return teacherRepository.findById(id).orElse(null); }
}