package org.example.employeeservice.repository;

import org.example.employeeservice.model.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionRepository  extends JpaRepository<Sanction, Long> {





}
