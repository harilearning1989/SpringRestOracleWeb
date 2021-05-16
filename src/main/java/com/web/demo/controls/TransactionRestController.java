package com.web.demo.controls;

import com.web.demo.dto.EmployeeDTO;
import com.web.demo.entities.Employee;
import com.web.demo.entities.EmployeeHealthInsurance;
import com.web.demo.exception.InvalidInsuranceAmountException;
import com.web.demo.services.TransactionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@Api(value = "TransactionRestController")
public class TransactionRestController {
    //https://www.javainuse.com/spring/boot-rollback
    //https://www.byteslounge.com/tutorials/spring-transaction-isolation-tutorial
    //https://stackoverflow.com/questions/8490852/spring-transactional-isolation-propagation
    //https://data-flair.training/blogs/spring-transaction-management/
    //https://www.tutorialspoint.com/spring/spring_transaction_management.htm

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/emp/list")
    public ResponseEntity<List<Employee>> getAllEmployeeEntity() {
        try {
            List<Employee> employeeEntities = transactionService.getAllEmployeeEntity();
            if (employeeEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employeeEntities, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllEmployeeEntity :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/save")
    public void saveEmployee(
            @RequestParam(defaultValue = "Hari") String empId,
            @RequestParam(required = false, defaultValue = "10") Integer coverage) throws InvalidInsuranceAmountException {
        Employee emp = new Employee();
        emp.setFirstName(empId);

        EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
        employeeHealthInsurance.setEmpId(empId);
        employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
        employeeHealthInsurance.setCoverageAmount(coverage);

        //transactionService.joinOrganization(emp, employeeHealthInsurance);
        transactionService.joinOrganizationRollBack(emp, employeeHealthInsurance);
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> updateEmp(@RequestBody EmployeeDTO dto) {
        Employee entity = transactionService.updateEmp(dto);
        return new ResponseEntity<Employee>(entity, HttpStatus.OK);
    }

    @GetMapping(value = "/uncommitted")
    public void readUncommitted() {

    }

}
