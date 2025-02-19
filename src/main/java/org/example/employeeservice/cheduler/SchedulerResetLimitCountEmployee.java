package org.example.employeeservice.cheduler;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerResetLimitCountEmployee {

    @Autowired
    private CompanyService companyService;

    @Scheduled(fixedRate = 15000)
    public void reset(){
        List<Company> allCompany = companyService.getAllCompany();

        for (Company company : allCompany) {
            List<Employee> employees = company.getEmployees();

            int sumAllSalary = 0;
            for (Employee employee : employees) {
               sumAllSalary += employee.getSalary();

            }
            company.setBalance(company.getBalance() + sumAllSalary);

            companyService.save(company);
        }



    }


}
