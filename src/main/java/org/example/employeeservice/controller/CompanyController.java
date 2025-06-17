package org.example.employeeservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.CompanyStatus;
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
    private static final long BALANCE_UPDATE_INTERVAL = 60_000*1; // 1 минута в миллисекундах

    @GetMapping("/admin")
    public String companyAdminPanel(@RequestParam(name = "password") String password,Model model){
        List<Company> allCompany = companyService.getAllCompany();//Получаем лист компаний
        List<Company> companyList = allCompany.stream()
                .sorted(Comparator.comparing(Company::getBalance).reversed())
                .toList();

        // Вычисляем время до следующего обновления баланса
        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTime = ((currentTimeMillis / BALANCE_UPDATE_INTERVAL) + 1) * BALANCE_UPDATE_INTERVAL;
        model.addAttribute("nextBalanceUpdateTime", nextUpdateTime);

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
                .toList();

        // Вычисляем время до следующего обновления баланса
        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTime = ((currentTimeMillis / BALANCE_UPDATE_INTERVAL) + 1) * BALANCE_UPDATE_INTERVAL;
        model.addAttribute("nextBalanceUpdateTime", nextUpdateTime);

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
    public String addCompany(
            @RequestParam String name,
            @RequestParam String president,
            @RequestParam String password,
            HttpServletRequest request,
            Model model
    ) {
        // Получаем IP адрес клиента
        String clientIp = getClientIp(request);
        
        // Проверяем, не создавал ли этот IP уже компанию
        if (companyService.existsByCreatorIp(clientIp)) {
            model.addAttribute("error", "Вы уже создавали компанию в этом сезоне.Можно создать только одну компанию.");
            return "add_company";
        }

        Company company = new Company();
        company.setName(name);
        company.setPresident(president);
        company.setPassword(password);
        company.setCreatorIp(clientIp);

        companyService.save(company);
        model.addAttribute("isAdd", true);
        return "add_company";
    }

    @PostMapping("/{id}/update-status")
    public String updateCompanyStatus(
            @PathVariable Long id,
            @RequestParam CompanyStatus status,
            @RequestParam(name = "password") String password
    ) {
        if (!"admin-zero".equals(password)) {
            return "redirect:/company?error=unauthorized";
        }

        Company company = companyService.findCompanyById(id);
        if (company != null) {
            company.setStatus(status);
            companyService.save(company);
        }
        return "redirect:/company";
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    @GetMapping("/admin-login")
    public String showAdminLogin() {
        return "admin-login";
    }

//    @GetMapping("/admin")
//    public String adminPanel(@RequestParam String password) {
//        return "redirect:/company?password=" + password;
//    }
}
