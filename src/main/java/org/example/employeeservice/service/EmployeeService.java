package org.example.employeeservice.service;

import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Integer getRandomSalary() {//Метод который делает рандом зп
        Random rand = new Random();
        int randomCount = rand.nextInt(1,10);
        int randomCountVIP = rand.nextInt(1,100);

        if (randomCountVIP == 77){
            return 1000;
        }

        if(randomCount == 1){
            return rand.nextInt(100,300);
        }


        return rand.nextInt(10,100);
    }


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
