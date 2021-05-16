package com.web.demo.services;

import com.web.demo.entities.CropInsurance;

import java.util.List;

public interface IPlsqlService {
    public String procedureTest();
    public List<String> getArrayFunc();
    public List<CropInsurance> fetchCropInsuranceProcCursor();
}
