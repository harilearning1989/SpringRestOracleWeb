package com.web.demo.repos;

import com.web.demo.entities.CountriesEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISortingRepository
        extends PagingAndSortingRepository<CountriesEntity, Long> {

}