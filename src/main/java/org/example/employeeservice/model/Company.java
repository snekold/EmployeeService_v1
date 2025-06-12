package org.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

import java.util.List;

@Entity
@Data
public class Company { //class для компаний

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String president;
    private int countAddEmployeeThisDay;
    private String password;
    private long balance;
    
    @Column(name = "creator_ip")
    private String creatorIp;

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Robot> robots = new ArrayList<>();

    public  void plusCount(){
       countAddEmployeeThisDay++;
    }


}
