package com.university.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SessionController {
    @Autowired private SessionService sessionService;
    @Autowired private SessionRepository sessionRepository;
    @Autowired private ModuleService moduleService;
    @Autowired private StudentRepository studentRepository;

    @GetMapping("/sessions")
    public String listSessions(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            model.addAttribute("sessions", sessionService.getAllSessions());
        } else {
            Student student = studentRepository.findByEmail(currentUsername);
            if (student != null) {
                model.addAttribute("sessions", sessionRepository.findByGroupName(student.getGroupName()));
            } else {
                model.addAttribute("sessions", sessionService.getAllSessions());
            }
        }
        return "session_list";
    }

    @GetMapping("/session/new")
    public String showForm(Model model) {
        model.addAttribute("session", new Session());
        model.addAttribute("modules", moduleService.getAllModules());
        return "session_form";
    }

    @PostMapping("/session/save")
    public String saveSession(@ModelAttribute Session session, Model model) {
        try {
            sessionService.saveSession(session);
            return "redirect:/sessions";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("modules", moduleService.getAllModules());
            return "session_form";
        }
    }

    @GetMapping("/session/delete/{id}")
    public String deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return "redirect:/sessions";
    }

    // ✅ NEW EDIT METHOD
    @GetMapping("/session/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("session", sessionService.getSessionById(id));
        model.addAttribute("modules", moduleService.getAllModules()); // Dropdown data
        return "session_form";
    }
}