package com.university.management;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dayOfWeek;  // "Lundi", "Mardi"
    private String startTime;  // "08:00"
    private String endTime;    // "10:00"
    private String room;       // "Salle 101"

    // NEW: We need to know WHICH group is taking this class
    private String groupName;  // "G1", "G2"...

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}