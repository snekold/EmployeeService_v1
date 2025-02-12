package org.example.employeeservice.repository;

import org.example.employeeservice.model.Robber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobberRepository extends JpaRepository<Robber, Long>  {
}
