package com.web.demo.services;

import com.web.demo.entities.CountriesEntity;

public interface ICountriesService {

    public Iterable<CountriesEntity> getAllCountries();
}
