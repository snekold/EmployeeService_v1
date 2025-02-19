package org.example.employeeservice.service;

import org.example.employeeservice.model.Company;
import org.example.employeeservice.model.Robber;
import org.example.employeeservice.repository.CompanyRepository;
import org.example.employeeservice.repository.RobberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RobberSerivce {

    @Autowired
    private RobberRepository robberRepository;



    public Robber save(Robber robber){
        return robberRepository.save(robber);
    }

    public  Robber findRobberById(Long id){
        Optional<Robber> robber = robberRepository.findById(id);
       return robber.orElse(null);
    }

    public List<Robber> TakeAllRobber(){
        return robberRepository.findAll();
    }

    public void deleteRobber(Robber robber){
        robberRepository.delete(robber);
    }

    public void updateRobber(Robber robber){
        robberRepository.findById(robber.getId()).orElseThrow();

         robberRepository.save(robber);

    }


}
