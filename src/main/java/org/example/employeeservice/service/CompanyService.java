package org.example.employeeservice.service;

import lombok.AllArgsConstructor;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {

    private CompanyRepository companyRepository;

    //C
    public void save(Company company) {
        companyRepository.save(company);
    }

    public void saveAll(List<Company> companies) {
        companyRepository.saveAll(companies);
    }

    //R
    public Company findCompanyById(Long id) {
        Optional<Company> company =  companyRepository.findById(id);
        return company.orElse(null);
    }

    //R all
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    //D
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    // U PUT
    public void updateCompany(Company company){
        companyRepository.findById(company.getId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.save(company);
    }

    //reset limit employee
    public  void restartCompany(){
        for (Company company : companyRepository.findAll()) {
            company.setCountAddEmployeeThisDay(0);
        }
    }

    public Company findByName(String name) {
        return companyRepository.findByName(name).orElse(null);
    }

    public boolean existsByCreatorIp(String creatorIp) {
        return companyRepository.existsByCreatorIp(creatorIp);
    }

    //метод обнуления который получит так же из репозитория все компании и сросит их кол-во сотрудников добавленных в день на 0
//    public Company findCompanyByEnoughtEmployee(Employee employee) {
//
//      }
}
