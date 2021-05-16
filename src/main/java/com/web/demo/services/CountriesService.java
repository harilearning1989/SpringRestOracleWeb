package com.web.demo.services;

import com.web.demo.entities.CountriesEntity;
import com.web.demo.repos.ICountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountriesService implements ICountriesService{

    @Autowired
    private ICountriesRepository iCountriesRepository;
    @Override
    public Iterable<CountriesEntity> getAllCountries() {
        return iCountriesRepository.findAll();
    }
}
