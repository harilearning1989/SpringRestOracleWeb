package com.web.demo.services;

import com.web.demo.entities.CountriesEntity;
import com.web.demo.entities.Employee;
import com.web.demo.entities.EmployeeAudit;
import com.web.demo.repos.EmployeeAuditRepo;
import com.web.demo.repos.EmployeeRepo;
import com.web.demo.repos.ISortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo repo;
    @Autowired
    private EmployeeAuditRepo employeeAuditRepo;

    @Override
    @Cacheable(value = "employees", key = "#employee", unless = "#result.followers < 12000")
    public List<Employee> listAll() {
        return repo.findAll();
    }
    @Override
    public void save(Employee employee) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        repo.save(employee);
        EmployeeAudit employeeAudit = new EmployeeAudit();
        employeeAudit.setFirstName(employee.getFirstName());
        employeeAudit.setLastName(employee.getLastName());
        employeeAudit.setCompName(employee.getCompanyName());
        employeeAudit.setCreatedBy(username);
        employeeAudit.setModifiedBy(username);
        employeeAuditRepo.save(employeeAudit);
    }
    @Override
    public Employee get(Integer id) {
        return repo.findById(id).get();
    }
    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Autowired
    private ISortingRepository repository;

    public List<CountriesEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<CountriesEntity> pagedResult = repository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<CountriesEntity> getAllEmployeesDescending(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<CountriesEntity> pagedResult = repository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<CountriesEntity> getEmpPagingOnly(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<CountriesEntity> pagedResult = repository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<CountriesEntity> getEmpSortingOnly(String first, String second) {
        Sort emailSort = Sort.by(first);
        Sort firstNameSort = Sort.by(second);
        Sort groupBySort = emailSort.and(firstNameSort);
        List<CountriesEntity> list = (List<CountriesEntity>) repository.findAll(groupBySort);
        return list;
    }

    public List<CountriesEntity> getEmpSortOrder(){
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order nameSort = new Sort.Order(Sort.Direction.DESC, "region");
        orders.add(nameSort);
        Sort.Order regionSort = new Sort.Order(Sort.Direction.ASC, "name");
        orders.add(regionSort);

        List<CountriesEntity> countriesList = (List<CountriesEntity>) repository.findAll(Sort.by(orders));
        return countriesList;
    }

    public List<CountriesEntity> getEmpSortNullsLast(){
        Sort.Order nameSort = new Sort.Order(Sort.Direction.DESC, "region").nullsLast();
        List<CountriesEntity> countriesList = (List<CountriesEntity>) repository.findAll(Sort.by(nameSort));
        return countriesList;
    }
    public List<CountriesEntity> getEmpSortNullsFirst(){
        Sort.Order nameSort = new Sort.Order(Sort.Direction.DESC, "region").nullsFirst();
        List<CountriesEntity> countriesList = (List<CountriesEntity>) repository.findAll(Sort.by(nameSort));
        return countriesList;
    }

}
