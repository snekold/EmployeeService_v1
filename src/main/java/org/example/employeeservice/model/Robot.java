package org.example.employeeservice.model;

import jakarta.persistence.*;

@Entity
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salaryInHour;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;

}