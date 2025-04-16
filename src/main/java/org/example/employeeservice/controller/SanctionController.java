package org.example.employeeservice.controller;

import lombok.AllArgsConstructor;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.service.CompanyService;
import org.example.employeeservice.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SanctionController {

    private SanctionService sanctionService;
    private CompanyService companyService;

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


       Company companyTarget =  companyService.findByName(targetCompany);
        if (companyTarget == null) {
            model.addAttribute("error","Компания против которой вы вводите санкции не существует");
            return "add-sanction";
        }


        //илья
        //создать объект санкции и внетдрить в него все необходимое и сохранить в бд через санктион репозиторий



        return "add-sanction";
    }
}
