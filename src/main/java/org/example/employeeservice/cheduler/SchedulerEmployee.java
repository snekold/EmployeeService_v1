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
import java.util.Random;

@Component
public class SchedulerEmployee {

    @Autowired
    private CompanyService companyService;//Внедрение Сервиса Компании
    @Autowired
    private SanctionService sanctionService;//Внедрение Сервиса Санкции


    Random random = new Random();   

    //@Scheduled(fixedRate = 3600б000)
    @Scheduled(fixedRate = 60_000*1)   //Время через сколько будет обновляться баланс
    public void updateCompanyBalance() {//Метод для обновления баланса
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

    @Scheduled(cron = "0 0 3 * * *") // Каждый день в 00:00:00
    public void resetLimitCountEmployee() {//Метод применения санкций
        List<Company> allCompany = companyService.getAllCompany();//Получение всех компаний
        for (Company company : allCompany) {
            company.setCountAddEmployeeThisDay(0);

        }
        companyService.saveAll(allCompany);
    }

    @Scheduled(fixedRate = 60_000*1)//Время через сколько будет применяться санкция
    public void useSanction() {//Метод применения санкций

        List<Sanction> allSanctions = sanctionService.getAllSanctions();//Получение всех санкций

        for (Sanction sanction : allSanctions) {//Проходимся фором по санкциям
            if (!sanction.isProcessed()) {//Если санкция не обработана

                boolean randomBul = random.nextBoolean();
                Company fromCompany = companyService.findByName(sanction.getFromCompany());
                Company toCompany = companyService.findByName(sanction.getToCompany());
                long sanctionSum = sanction.getSanctionSum();
                if (randomBul) {//исполнение санкции если ранадом true
                    sanction.setSanctionStatus(true);//исполнилась


                    toCompany.setBalance(toCompany.getBalance() - sanctionSum);
                    fromCompany.setBalance(fromCompany.getBalance() + sanctionSum);

                    companyService.save(fromCompany);
                    companyService.save(toCompany);

                    sanction.setProcessed(true);
                    sanction.setSanctionStatus(true);
                    sanctionService.addSanction(sanction);
                } else { //отклонение санкции
                    sanction.setSanctionStatus(false);
                    sanction.setProcessed(true);
                    sanctionService.addSanction(sanction);

                    fromCompany.setBalance(fromCompany.getBalance()-sanctionSum);
                    companyService.save(fromCompany);
                }


            }
        }
    }
}
