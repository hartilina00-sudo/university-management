package com.university.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModuleController {
    @Autowired private ModuleService moduleService;
    @Autowired private TeacherService teacherService;

    @GetMapping("/modules")
    public String listModules(Model model) {
        model.addAttribute("modules", moduleService.getAllModules());
        return "module_list";
    }
    @GetMapping("/module/new")
    public String showForm(Model model) {
        model.addAttribute("module", new Module());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "module_form";
    }
    @PostMapping("/module/save")
    public String saveModule(@ModelAttribute Module module) {
        moduleService.saveModule(module);
        return "redirect:/modules";
    }
    @GetMapping("/module/delete/{id}")
    public String deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return "redirect:/modules";
    }

    // ✅ NEW EDIT METHOD
    @GetMapping("/module/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("module", moduleService.getModuleById(id));
        model.addAttribute("teachers", teacherService.getAllTeachers()); // Dropdown data
        return "module_form";
    }
}