package org.example.employeeservice.data;

import lombok.Data;
import org.example.employeeservice.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class DataBase {
    public List<Employee> employees = new ArrayList<>();


}
