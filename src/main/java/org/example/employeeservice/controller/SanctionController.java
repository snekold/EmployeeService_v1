package org.example.employeeservice.controller;

import lombok.AllArgsConstructor;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Sanction;
import org.example.employeeservice.service.CompanyService;
import org.example.employeeservice.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class SanctionController {

    private SanctionService sanctionService;
    private CompanyService companyService;

    @GetMapping("/sanctions")
    public String sanction(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<Sanction> sanctionsPage = sanctionService.getSanctionsWithPagination(page, size);
        model.addAttribute("sanctions", sanctionsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sanctionsPage.getTotalPages());
        model.addAttribute("totalItems", sanctionsPage.getTotalElements());
        return "sanctions";
    }

    @GetMapping("/sanction/{id}details")
    public String sanctionDetails(Model model, @PathVariable int id){
        return "sanctions";
    }

    @GetMapping("/add-sanction")
    public String addSanction() {
        return "add-sanction";
    }

    @PostMapping("/add-sanction")
    public String addSanctionPost(
            @RequestParam("issuingCompany") String nameCompany, // имя компании создающей санкцию
            @RequestParam String companyPassword,
            @RequestParam String targetCompany,
            @RequestParam Long sanctionAmount,// сумма санкции
            Model model
    ) {
        //получение компании по имени (компания которая хочет ввести санкции)
        Company company = companyService.findByName(nameCompany);

        if (company == null) {
            model.addAttribute("error", "Ваша компания не зарегистрирована");
            return "add-sanction";
        }

        if (!company.getPassword().equals(companyPassword)) {
            model.addAttribute("error","Пароль компании не совпадает с правильным");
            return "add-sanction";
        }

        if (company.getBalance() < sanctionAmount) {
            model.addAttribute("error","Какие санкции!?Вы баланс свой видели??!");
            return "add-sanction";
        }

        if (sanctionAmount <= 0) {
            model.addAttribute("error","За это можно и Бан получить!");
            return "add-sanction";
        }

        //получение компании против которой идет ввод санкции
        Company companyTarget =  companyService.findByName(targetCompany);

        if (companyTarget == null) {
            model.addAttribute("error","Компания против которой вы вводите санкции не существует");
            return "add-sanction";
        }

        Sanction sanction = new Sanction();
         sanction.setSanctionSum(sanctionAmount);
         sanction.setToCompany(targetCompany);
         sanction.setFromCompany(nameCompany);

        sanctionService.addSanction(sanction);//в бд сохраняем санкции
        return "add-sanction";
    }
}
