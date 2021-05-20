package com.web.demo.services;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.entities.Employee;
import com.web.demo.entities.EmployeeHealthInsurance;
import com.web.demo.exception.InvalidInsuranceAmountException;
import com.web.demo.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    HealthInsuranceService healthInsuranceService;

    @Override
    public List<Employee> getAllEmployeeEntity() {
        return employeeRepo.findAll();
    }

    @Override
    @Transactional
    public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance)
            throws InvalidInsuranceAmountException {
        employeeRepo.save(employee);
        try {
            healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
        } catch (InvalidInsuranceAmountException e) {
            throw new InvalidInsuranceAmountException("Exception is thrown");
        }
    }

    @Override
    @Transactional(rollbackFor = InvalidInsuranceAmountException.class)
    public void joinOrganizationRollBack(Employee employee, EmployeeHealthInsurance employeeHealthInsurance)
            throws InvalidInsuranceAmountException {
        employeeRepo.save(employee);
        try {
            healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
        } catch (InvalidInsuranceAmountException e) {
            throw new InvalidInsuranceAmountException("Exception is thrown");
        }
    }

    @Override
    public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
        employeeRepo.deleteById(1);
        healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
    }

    @Override
    @Transactional(rollbackFor = NullPointerException.class,
            noRollbackFor = EntityNotFoundException.class)
    public Employee updateEmp(EmployeeDTO dto) {
        Employee entity = employeeRepo.findById(1).get();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setCounty(dto.getCounty());
        entity.setCity(dto.getCity());
        System.out.println(1/0);
        entity.setCompanyName(dto.getCompanyName());
        entity.setState(dto.getState());
        entity.setZip(dto.getZip());
        return entity;
    }
}
