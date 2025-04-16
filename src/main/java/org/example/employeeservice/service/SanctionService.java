package org.example.employeeservice.service;

import lombok.AllArgsConstructor;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Sanction;
import org.example.employeeservice.repository.CompanyRepository;
import org.example.employeeservice.repository.SanctionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SanctionService {

    private SanctionRepository sanctionRepository;
    private CompanyRepository companyRepository;






    //допистать добавление санкции
    public void addSanction(Sanction sanction) {

    }

    //получение санкции по id

    //получение санкций всех
}
