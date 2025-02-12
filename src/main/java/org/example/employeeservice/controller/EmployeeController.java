package org.example.employeeservice.controller;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.CompanyRepository;
import org.example.employeeservice.repository.EmployeeRepository;
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

    @GetMapping("/employees/add")
    public String addEmployees() {
        return "add-employee";
    }


    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String jobTitle,
                              @RequestParam String name_company,
                              @RequestParam String password_company,
                              Model model

    ) {
        System.out.println(name_company);
        Company company = companyRepository.findByName(name_company);
        if (company == null) {
            model.addAttribute("message", "Company not found");
            return "add-employee";
        }

        if (!password_company.equals(company.getPassword())) {
            model.addAttribute("message", "Password do not match");
            return "add-employee";
        }

        if (company.getCountAddEmployeeThisDay() >= 3){
            model.addAttribute("message", "Вы добавили уже сегодня 3 сотрудников.Не нарушайте!");
            return "add-employee";
        }


        Employee employee = new Employee();
        employee.setName(name);
        employee.setJobTitle(jobTitle);

        employee.setCompany(company);

        //прибавить 1 к добавленным сотрудникам за текущий день (нужно для проверки лимита 3 сотрудника в день)
        company.setCountAddEmployeeThisDay(company.getCountAddEmployeeThisDay()+1);


        companyRepository.save(company);
        employeeRepository.save(employee);

        return "add-employee";
    }


}