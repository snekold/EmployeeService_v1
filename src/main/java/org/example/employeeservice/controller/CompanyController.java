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
    private CompanyService companyService; //Внедряем сервис компании

    @GetMapping("/admin")
    public String companyAdminPanel(@RequestParam(name = "password") String password,Model model){
        List<Company> allCompany = companyService.getAllCompany();//Получаем лист компаний
        List<Company> companyList = allCompany.stream()
                .sorted(Comparator.comparing(Company::getBalance).reversed())
                .toList();//Непонятно что тут с 30 строчки до 32

        if (password.equals("admin-zero")){
            model.addAttribute("companies", companyList);//Добавляем атрибут компании
            return "company-admin";
        }else {
            model.addAttribute("companies", companyList);//Добавляем атрибут компании
            return "companies";
        }
    }


    @GetMapping("/{id}/delete")
    public String company(@PathVariable Long id) { // Содаем метод
       companyService.deleteCompanyById(id);//Получаем лист компаний
        return "redirect:/company";
    }

    @GetMapping()
    public String company(Model model) { // Содаем метод
        List<Company> allCompany = companyService.getAllCompany();//Получаем лист компаний


        List<Company> companyList = allCompany.stream()
                .sorted(Comparator.comparing(Company::getBalance).reversed())
                .toList();//Непонятно что тут с 30 строчки до 32


        model.addAttribute("companies", companyList);//Добавляем атрибут компании

        return "companies";//Возвращаем компании
    }



    @GetMapping("/add-company")
    public String addCompany() {
        return "add_company";
    }//Делаем метод добавить компанию

   @GetMapping("/president-info")
   public String presidentInfo(){ return "president-info";}//Информация о владельце

    @PostMapping("/addCompany")//Делаем пост запрос для добавления компании
    public String addCompany(@RequestParam String name,
                             @RequestParam String president,
                             @RequestParam String password,
                             Model model
    ) {

        Company company = new Company();//Делаем объект и параметризируем его
        company.setName(name);
        company.setPresident(president);
        company.setPassword(password);

        companyService.save(company);//сохраняем компании в бд(Бд-База Данных)
        model.addAttribute("isAdd", true);
        return "add_company";
    }


}
