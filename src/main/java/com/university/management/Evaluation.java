package com.university.management;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; // 👈 Import this

@Entity
@Data
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Grades
    private Double noteTP;
    private Double noteDS;
    private Double noteProjet;
    private Double noteParticipation;
    private Double noteFinale;

    private String feedback;

    // ✅ THE FIX: Exclude relationships from ToString
    @ManyToOne
    @ToString.Exclude // 👈 Add this
    private Student student;

    @ManyToOne
    @ToString.Exclude // 👈 Add this
    private Module module;

    public void calculateFinalGrade() {
        // (TP + DS + Projet + Part) / 4
        // Treat null as 0.0 to avoid errors
        double tp = (noteTP != null) ? noteTP : 0.0;
        double ds = (noteDS != null) ? noteDS : 0.0;
        double pr = (noteProjet != null) ? noteProjet : 0.0;
        double pa = (noteParticipation != null) ? noteParticipation : 0.0;

        this.noteFinale = (tp + ds + pr + pa) / 4.0;
    }
}