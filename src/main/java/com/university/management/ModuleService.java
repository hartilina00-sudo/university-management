package com.university.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAllModules() { return moduleRepository.findAll(); }
    public void saveModule(Module m) { moduleRepository.save(m); }
    public void deleteModule(Long id) { moduleRepository.deleteById(id); }

    // ✅ NEW METHOD
    public Module getModuleById(Long id) { return moduleRepository.findById(id).orElse(null); }
}