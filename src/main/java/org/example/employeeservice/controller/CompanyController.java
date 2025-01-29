package org.example.employeeservice.controller;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping("/add-company")
    public String addCompany() {
        return "add_company";
    }


    @PostMapping("/addCompany")
    public String addCompany(@RequestParam String name,
                             @RequestParam String president,
                             @RequestParam String password,
                             Model model
    ) {

        Company company = new Company();
        company.setName(name);
        company.setPresident(president);
        company.setPassword(password);

        companyService.save(company);
        model.addAttribute("isAdd", true);


        return "add_company";
    }


}
