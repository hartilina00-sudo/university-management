package com.university.management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> getAllEvaluations() { return evaluationRepository.findAll(); }
    public void saveEvaluation(Evaluation e) { evaluationRepository.save(e); }
    public void deleteEvaluation(Long id) { evaluationRepository.deleteById(id); }

    // ✅ NEW METHOD
    public Evaluation getEvaluationById(Long id) { return evaluationRepository.findById(id).orElse(null); }
}