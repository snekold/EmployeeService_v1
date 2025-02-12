package org.example.employeeservice.cheduler;



import org.example.employeeservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerResetLimitCountEmployee {

    @Autowired
    private CompanyService companyService;

    @Scheduled(cron = "0 * * * *  ?")
    public void reset(){
    }


}
