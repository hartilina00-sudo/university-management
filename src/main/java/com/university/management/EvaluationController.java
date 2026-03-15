package com.university.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/evaluations")
    public String listEvaluations(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            model.addAttribute("evaluations", evaluationService.getAllEvaluations());
        } else {
            model.addAttribute("evaluations", evaluationRepository.findByStudentEmail(currentUsername));
        }
        return "evaluation_list";
    }

    @GetMapping("/evaluation/new")
    public String showForm(Model model) {
        model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("modules", moduleService.getAllModules());
        return "evaluation_form";
    }

    @PostMapping("/evaluation/save")
    public String saveEvaluation(@ModelAttribute Evaluation evaluation) {
        // Auto-calculate before saving (works for both New and Edit)
        evaluation.calculateFinalGrade();
        evaluationService.saveEvaluation(evaluation);
        return "redirect:/evaluations";
    }

    // ✅ NEW: Delete Method
    @GetMapping("/evaluation/delete/{id}")
    public String deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return "redirect:/evaluations";
    }

    // ✅ NEW: Edit Method
    @GetMapping("/evaluation/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("evaluation", evaluationService.getEvaluationById(id));
        // We MUST reload these lists so the dropdowns work!
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("modules", moduleService.getAllModules());
        return "evaluation_form";
    }
}