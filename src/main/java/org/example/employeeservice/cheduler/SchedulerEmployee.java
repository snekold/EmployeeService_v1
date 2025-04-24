package org.example.employeeservice.cheduler;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.model.Sanction;
import org.example.employeeservice.service.CompanyService;
import org.example.employeeservice.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerEmployee {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SanctionService sanctionService;

    //@Scheduled(fixedRate = 3600б000)
    @Scheduled(fixedRate = 10000)
    public void updateCompanyBalance(){
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
    @Scheduled(fixedRate = 3600000)
    public void useSanction(){
        List<Sanction> allSanctions = sanctionService.getAllSanctions();

        for (Sanction sanction : allSanctions) {
            if (sanction.getSanctionStatus() == false) {
                sanction.setSanctionStatus(true);
                // ИСПОЛНИТЬ САНКЦИЮ
            }
        }
    }
}
