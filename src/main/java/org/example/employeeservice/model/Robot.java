package org.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Robot {
    @Id
    @GeneratedValue
    private String name;
    private double salaryInHour;

}