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

        if(randomCount == 1){
            return rand.nextInt(2000,5000);
        }

        return rand.nextInt(100,1000);
    }


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
