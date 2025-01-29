package org.example.employeeservice.service;

import org.example.employeeservice.model.Company;
import org.example.employeeservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService  {

    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company) {
       Company saveCompany =  companyRepository.save(company);
        return saveCompany;
    }

}
