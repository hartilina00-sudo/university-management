package com.university.management;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; // 👈 Import this!
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String academicLevel;
    private String groupName;
    private String stream;
    private String skills;

    // ✅ THE FIX: Break the infinite loop
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude  // 👈 Add this line to stop the crash
    private List<Evaluation> evaluations;
}