package org.example.employeeservice.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Sanction;
import org.example.employeeservice.repository.CompanyRepository;
import org.example.employeeservice.repository.SanctionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SanctionService {

    private SanctionRepository sanctionRepository;
    private CompanyRepository companyRepository;





    //допистать добавление санкции
    public void addSanction(Sanction sanction) {
        sanctionRepository.save(sanction);

    }


    //получение санкции по id
    public Sanction getById(Long id){
       Sanction sanction =  sanctionRepository.findById(id).get();
        return sanction;
    }
    //получение санкций всех
    public List<Sanction> getAllSanctions(){
        List<Sanction> sanctionList = sanctionRepository.findAll();
        sanctionList.sort(Comparator.comparing(Sanction::getId).reversed());
        return sanctionList;
    }
}
