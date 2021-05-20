package com.web.demo.repos;

import com.web.demo.entities.EmployeeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAuditRepo extends JpaRepository<EmployeeAudit, Integer> {
}
