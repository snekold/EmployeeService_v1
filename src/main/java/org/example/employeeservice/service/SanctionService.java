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



    public Company findSanctionsByName(String name){

//
//        Optional<Company> optionalCompany  = companyRepository.findByName(name);
//        if (optionalCompany.isPresent()){
//            Company company = optionalCompany.get();
//            return company;
//        }
//        return null;
    }



    public void addSanction(Sanction sanction) {

    }

    public void takeSanction(Sanction sanction) {

    }
}
