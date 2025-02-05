package org.example.employeeservice.controller;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.CompanyRepository;
import org.example.employeeservice.repository.EmployeeRepository;
import org.example.employeeservice.repository.RobberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RobberRepository robberRepository;

    @GetMapping("/employees/add")
    public String addEmployees() {
        return "add-employees";
    }


    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String jobTitle,
                              @RequestParam String name_company,
                              @RequestParam String password_company,
                              Model model

    ) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setJobTitle(jobTitle);

        Company company = companyRepository.findByName(name_company);

        if (company == null) {
            model.addAttribute("message", "Company not found");
            return "add-employees";
        }

        if (!password_company.equals(company.getPassword())) {
            model.addAttribute("message", "Password do not match");
            return "add-employees";
        }


        employee.setCompany(company);
        employeeRepository.save(employee);
        employeeRepository.save(robber);

        return "add-employees";
    }


}