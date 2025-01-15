package org.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue

    private long id;
    private String name;
    private String jobTitle;
    private int salary;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;



}
