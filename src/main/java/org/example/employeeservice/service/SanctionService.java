package org.example.employeeservice.service;

import lombok.AllArgsConstructor;
import org.example.employeeservice.model.Sanction;
import org.example.employeeservice.repository.SanctionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SanctionService {

    private SanctionRepository sanctionRepository;

    public void addSanction(Sanction sanction) {

    }

    public void takeSanction(Sanction sanction) {

    }
}
