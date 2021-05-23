package com.web.demo.repos;

import com.web.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Procedure(name = "show_people_data",value = "show_people_data")
    Object[] getPeopleData(@Param("my_param_in") String myParamIn);
}
