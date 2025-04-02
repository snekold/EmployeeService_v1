package org.example.employeeservice.service;

import org.example.employeeservice.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployeeService {
    public Integer getRandomSalary() {
        Random rand = new Random();
        int randomCount = rand.nextInt(1,10);

        if(randomCount == 1){
            return rand.nextInt(2000,5000);
        }

        return rand.nextInt(100,1000);
    }


}
