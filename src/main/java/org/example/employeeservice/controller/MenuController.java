package org.example.employeeservice.controller;

import lombok.AllArgsConstructor;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {

    private CompanyService companyService;

    @GetMapping("/")//Обработка гет запроса на переход в меню
    public String Index(Model model) {
        List<Company> companiesList = companyService.getAllCompany();
        Collections.sort(companiesList, Comparator.comparing(Company::getBalance).reversed());

        companiesList = companiesList.stream()
                .limit(3)
                .toList();



        model.addAttribute("companies", companiesList);
        return "index";
    }
}
