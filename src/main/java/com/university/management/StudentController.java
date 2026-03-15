package com.university.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student_list";
    }

    @GetMapping("/student/new")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    // ✅ THE SMART FIX
    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute Student student) {
        // Check if we are UPDATING an existing student
        if (student.getId() != null) {
            Student existing = studentService.getStudentById(student.getId());
            if (existing != null) {
                // Manually update fields to PRESERVE the Grades (Evaluations)
                existing.setFirstName(student.getFirstName());
                existing.setLastName(student.getLastName());
                existing.setEmail(student.getEmail());
                existing.setAcademicLevel(student.getAcademicLevel());
                existing.setGroupName(student.getGroupName());
                existing.setStream(student.getStream());
                existing.setSkills(student.getSkills());

                // Do NOT touch existing.setEvaluations() -> This keeps grades safe!

                studentService.saveStudent(existing);
                return "redirect:/students";
            }
        }

        // If it's a NEW student, just save normally
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student_form";
    }
}