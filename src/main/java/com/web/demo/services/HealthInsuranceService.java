package com.web.demo.services;

import com.web.demo.entities.EmployeeHealthInsurance;
import com.web.demo.exception.InvalidInsuranceAmountException;

public interface HealthInsuranceService {
    void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance)
            throws InvalidInsuranceAmountException;

    void deleteEmployeeHealthInsuranceById(String empid);
}
