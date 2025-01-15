package org.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String president;
    private int countAddEmployeeThisDay;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();



}
