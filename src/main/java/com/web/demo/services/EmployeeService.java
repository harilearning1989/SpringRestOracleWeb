package com.web.demo.services;

import com.web.demo.entities.CountriesEntity;
import com.web.demo.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> listAll();

    void save(Employee product);

    Employee get(Integer id);

    void delete(Integer id);
    List<CountriesEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);
    List<CountriesEntity> getAllEmployeesDescending(Integer pageNo, Integer pageSize, String sortBy);
    List<CountriesEntity> getEmpPagingOnly(Integer pageNo, Integer pageSize);
    List<CountriesEntity> getEmpSortingOnly(String first, String second);
    List<CountriesEntity> getEmpSortOrder();
    List<CountriesEntity> getEmpSortNullsLast();
    List<CountriesEntity> getEmpSortNullsFirst();
}
