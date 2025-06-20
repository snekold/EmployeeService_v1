package org.example.employeeservice.controller;


import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.CompanyRepository;
import org.example.employeeservice.repository.EmployeeRepository;
import org.example.employeeservice.service.CompanyService;
import org.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/add")
    public String addEmployees() {
        return "add-employee";
    }

    @GetMapping("/employees/{id_company}")
    public String employees(@PathVariable(name = "id_company") long id_company, Model model) {
        Company company = companyService.findCompanyById(id_company);
        List<Employee> employees = company.getEmployees();

        model.addAttribute("employees", employees);
        return "company-info";
    }


    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String name_company,
                              @RequestParam String password_company,
                              Model model

    ) {
        //добавление сотрудника в компанию
        System.out.println(name_company);
        Company company = companyService.findByName(name_company);

        //проверка что компания not существует
        if (company == null) {
            model.addAttribute("message", "Company not found");
            return "add-employee";
        }

        // check password company != password from input
        if (!password_company.equals(company.getPassword())) {
            model.addAttribute("message", "Password do not match");
            return "add-employee";
        }

        // check count added employee this day
        if (company.getCountAddEmployeeThisDay() >= 3) {
            model.addAttribute("message", "Вы добавили уже сегодня 3 сотрудников.Не нарушайте!");
            return "add-employee";
        }


        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(employeeService.getRandomSalary());
        employee.setCompany(company);

        //прибавить 1 к добавленным сотрудникам за текущий день (нужно для проверки лимита 3 сотрудника в день)
        company.plusCount();


        companyService.save(company);
        employeeService.save(employee);


        model.addAttribute("message_added","Сотрудник добавлен с зарплатой " + employee.getSalary() +  " B-coin");
        return "add-employee";
    }


}