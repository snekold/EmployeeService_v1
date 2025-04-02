package org.example.employeeservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SanctionController {

    @GetMapping("/add-sanction")
    public String addSanction() {
        return  "add-sanction";
    }


    @PostMapping("/add-sanction")
    public String addSanctionPost(
            @RequestParam String issuingCompany,
            @RequestParam String companyPassword,
            @RequestParam String targetCompany,
            @RequestParam Long sanctionAmount)
    {

        System.out.println(issuingCompany);
        System.out.println(companyPassword);
        System.out.println(targetCompany);
        System.out.println(sanctionAmount);


        return "add-sanction";
    }
}
