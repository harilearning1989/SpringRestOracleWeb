package com.web.demo.services;

import com.web.demo.dto.EmployeeDTO;
import com.web.demo.entities.Employee;
import com.web.demo.entities.EmployeeHealthInsurance;
import com.web.demo.exception.InvalidInsuranceAmountException;

import java.util.List;

public interface TransactionService {
    List<Employee> getAllEmployeeEntity();

    public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance)
            throws InvalidInsuranceAmountException;

    public void joinOrganizationRollBack(Employee employee, EmployeeHealthInsurance employeeHealthInsurance)
            throws InvalidInsuranceAmountException;

    public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

    Employee updateEmp(EmployeeDTO dtoToEntity);
}
