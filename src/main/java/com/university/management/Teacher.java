package com.university.management;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    // Specific Teacher Fields
    private String grade;       // Prof, Maître Assistant...
    private String specialty;   // Java, Réseaux, Web...
    private int hourlyLoad;     // Charge Horaire

    // NEW: Compétences
    private String skills;
}