package org.example.employeeservice.controller;


import org.example.employeeservice.data.DataBase;
import org.example.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class EmployeeController {


   private static int ID = 1;
    Map<String, Integer> countEmployees = new HashMap<>();


    @Autowired
    private DataBase dataBase;

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", dataBase.getEmployees());
        return "employees";
    }

    @GetMapping("/president-info")
    public String presidentInfo() {
        return "president-info";
    }


    @GetMapping("/employees/add")
    public String addEmployeeGet(Model model) {
        return "add-employee";
    }


    @PostMapping("/employees/add")
    public String addEmployeePost(@RequestParam("name") String name,
                                  @RequestParam("jobTitle") String jobTitle,
                                  @RequestParam("name_president") String name_president,
                                  Model model) {


        int count = countEmployees.getOrDefault(name_president,0);
        if (count >= 3) {

            model.addAttribute("message",name_president + " вы сегодня превысили лимит по сотрудникам");
            return "add-employee";
        }

        countEmployees.put(name_president,count + 1);



        Employee employee = new Employee();
        employee.setName(name);
        employee.setId(ID++);
        employee.setJobTitle(jobTitle);


        Random random = new Random();


        int criticalNumber = random.nextInt(1, 10);
        int salary = 0;
        if (criticalNumber == 5) {
            salary = random.nextInt(5000, 15000);
        } else {
            salary = random.nextInt(100, 1000);
        }

        employee.setSalary(salary);



        dataBase.getEmployees().add(employee);
        return "redirect:/employees";
    }
}
