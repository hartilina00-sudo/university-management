package com.university.management;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // --- NEW FIELDS FROM REQUIREMENTS ---
    private String stream;            // Filière (Info, Gestion...)
    private String semester;          // Semestre (S1, S2...)
    private int hourlyVolume;         // Volume Horaire (e.g., 40h)
    private String requiredSkills;    // Compétences requises

    // "Enseignant Responsable" (Link to Teacher)
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher responsibleTeacher;
}