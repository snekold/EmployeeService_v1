package org.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromCompany;
    private String toCompany;


    private int companyPassword;
    private int sanctionSum;


}
