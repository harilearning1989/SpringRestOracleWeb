package com.web.demo.repos;

import com.web.demo.entities.CountriesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICountriesRepository extends CrudRepository<CountriesEntity, Integer>{

    public List<CountriesEntity> findByIntRegionNull();
    public List<CountriesEntity> findByIntRegionNotNull();

}
