package com.web.demo.services;

import com.web.demo.entities.City;
import com.web.demo.repos.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private ICityRepository repository;

    @Override
    public List<City> findAllOrderByPopulationAsc() {
        return repository.findAllOrderByPopulationAsc();
    }

    @Override
    public List<City> findAllOrderByNameAsc(String name) {
        Sort sort = Sort.by(name);
        return repository.findAllOrderByNameAsc(sort);
    }
}
