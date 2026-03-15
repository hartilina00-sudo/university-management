package com.university.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() { return studentRepository.findAll(); }
    public void saveStudent(Student s) { studentRepository.save(s); }
    public void deleteStudent(Long id) { studentRepository.deleteById(id); }

    // ✅ NEW METHOD
    public Student getStudentById(Long id) { return studentRepository.findById(id).orElse(null); }
}