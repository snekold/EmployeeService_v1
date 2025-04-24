package org.example.employeeservice.cheduler;



import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Employee;
import org.example.employeeservice.model.Sanction;
import org.example.employeeservice.service.CompanyService;
import org.example.employeeservice.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerEmployee {

    @Autowired
    private CompanyService companyService;//Внедрение Сервиса Компании
    @Autowired
    private SanctionService sanctionService;//Внедрение Сервиса Санкции

    //@Scheduled(fixedRate = 3600б000)
    @Scheduled(fixedRate = 10000)   //Время через сколько будет обновляться баланс
    public void updateCompanyBalance(){//Метод для обновления баланса
        List<Company> allCompany = companyService.getAllCompany();//Получение вссех компаний

        for (Company company : allCompany) {//Цикл фор который проходиться по листу allompany
            List<Employee> employees = company.getEmployees();//Получаем все компании после фора

            int sumAllSalary = 0;//Создаем переменную с суммой зарплаты
            for (Employee employee : employees) {//Проходимся фором по сотрудникам
               sumAllSalary += employee.getSalary();//складываем сумму с балансом

            }
            company.setBalance(company.getBalance() + sumAllSalary);//Добавляем зарплату к балансу
            companyService.save(company);//Сохраняем изменения баланса компании

        }



    }
    @Scheduled(fixedRate = 3600000)//Время через сколько будет применяться санкция
    public void useSanction(){//Метод применения санкций
        List<Sanction> allSanctions = sanctionService.getAllSanctions();//Получение всех санкций

        for (Sanction sanction : allSanctions) {//Проходимся фором по санкциям
            if (sanction.getSanctionStatus() == false) {//Если санкция не работает то фолс
                sanction.setSanctionStatus(true);//Если работает тру
                // ИСПОЛНИТЬ САНКЦИЮ
            }
        }
    }
}
