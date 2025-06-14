package org.example.employeeservice.service;

import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService  {

    @Autowired
    private CompanyRepository companyRepository;

    //C
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> saveAll(List<Company> companys) {
        return companyRepository.saveAll(companys);
    }
    //R
    public Company findCompanyById(Long id) {
        Optional<Company> company =  companyRepository.findById(id);
        return company.orElse(null);
    }

    //R all
    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }


    //D
    public void  deleteCompanyById(Long id){
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

    public Company findByName(String nameCompany) {
        return  companyRepository.findByName(nameCompany).orElse(null);
    }

    //метод обнуления который получит так же из репозитория все компании и сросит их кол-во сотрудников добавленных в день на 0
//    public Company findCompanyByEnoughtEmployee(Employee employee) {
//
//      }
}
