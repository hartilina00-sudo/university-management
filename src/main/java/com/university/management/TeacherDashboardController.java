package com.university.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherDashboardController {

    @Autowired private TeacherRepository teacherRepository;
    @Autowired private SessionRepository sessionRepository;
    @Autowired private ModuleRepository moduleRepository;
    @Autowired private EvaluationRepository evaluationRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        // Find teacher by email
        Teacher teacher = teacherRepository.findByEmail(principal.getName());

        if (teacher != null) {
            model.addAttribute("teacher", teacher);

            // Uses the nested property method names
            model.addAttribute("sessions", sessionRepository.findByModuleResponsibleTeacherId(teacher.getId()));
            model.addAttribute("modules", moduleRepository.findByResponsibleTeacherId(teacher.getId()));
            model.addAttribute("evaluations", evaluationRepository.findByModuleResponsibleTeacherId(teacher.getId()));
        }
        // FIXED: Return "teacher" to match src/main/resources/templates/teacher.html
        return "teacher";
    }
}