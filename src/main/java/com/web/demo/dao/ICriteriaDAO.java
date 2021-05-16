package com.web.demo.dao;

import com.web.demo.entities.CropInsurance;

import java.util.List;

public interface ICriteriaDAO {

    List<CropInsurance> getByMandal(String mandal);
    List<CropInsurance> getByMandalAndCrop(String mandal, String crop);
    List<CropInsurance> getByMandalOrCrop(String mandal, String crop);
}
