package org.example.employeeservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")//Обработка гет запроса на переход в меню
    public String Index() {
        return "index";
    }
}
