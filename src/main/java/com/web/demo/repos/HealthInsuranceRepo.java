package com.web.demo.repos;

import com.web.demo.entities.EmployeeHealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInsuranceRepo extends JpaRepository<EmployeeHealthInsurance, Long> {
}
