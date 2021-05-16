package com.web.demo.services;

import com.web.demo.dtos.*;

import java.util.List;

public interface CSVReadService {

    public List<EmployeeDTO> readEmployeeInfo();

    public List<CropInsuranceDTO> readCropDetails();

    public List<StudentDTO> readStudentInfo();

    public List<CountriesDTO> readCountriesRegions();

    public List<SalesOrderDTO> readSalesOrderDetails();

    public List<IndiaStatesDTO> getIndiaStates();

    public List<CropInsuranceDTO> readCsvFromClassPath();

    public List<CropInsuranceDTO> readCropByMandal(String mandalName);
}
