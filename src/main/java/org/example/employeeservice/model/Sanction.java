package org.example.employeeservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Sanction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromCompany;
    private String toCompany;
    private long sanctionSum;
    private Boolean sanctionStatus;


}
