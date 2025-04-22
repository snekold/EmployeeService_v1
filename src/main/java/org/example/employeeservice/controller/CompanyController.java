package org.example.employeeservice.controller;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping()
    public String company(Model model) {
        List<Company> allCompany = companyService.getAllCompany();


        List<Company> companyList = allCompany.stream()
                .sorted(Comparator.comparing(Company::getBalance).reversed())
                .toList();


        model.addAttribute("companies", companyList);

        return "companies";
    }



    @GetMapping("/add-company")
    public String addCompany() {
        return "add_company";
    }

   @GetMapping("/president-info")
   public String presidentInfo(){ return "president-info";}

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
