package org.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Employee { //КЛАСС СОТРУДНИКОВ
    @Id
    @GeneratedValue

    private long id;
    private String name;
    private String jobTitle;
    private int salary;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_company")
    private Company company;



}
