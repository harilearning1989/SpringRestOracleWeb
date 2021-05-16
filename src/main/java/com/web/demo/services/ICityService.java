package com.web.demo.services;

import com.web.demo.entities.City;

import java.util.List;

public interface ICityService {
    List<City> findAllOrderByPopulationAsc();
    List<City> findAllOrderByNameAsc(String name);
}
