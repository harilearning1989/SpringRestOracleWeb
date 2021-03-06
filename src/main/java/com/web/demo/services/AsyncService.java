package com.web.demo.services;

import com.web.demo.dto.*;
import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.dtos.StudentDTO;

import java.util.List;

public interface AsyncService {
    List<CropInsuranceDTO> readCropDetails(String s);

    List<StudentDTO> readStudentInfo(String s);

    List<EmployeeDTO> readEmployeeInfo(String s);

    List<Countries> readCountriesRegions(String s);

    List<SalesOrderDTO> readSalesOrderDetails(String s);
}
