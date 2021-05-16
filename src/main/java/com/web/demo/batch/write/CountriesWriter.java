package com.web.demo.batch.write;

import com.web.demo.entities.Countries;
import com.web.demo.reposDev.CountriesRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CountriesWriter implements ItemWriter<Countries> {

    @Autowired
    private CountriesRepository repo;

    @Override
    @Transactional
    public void write(List<? extends Countries> countries) throws Exception {
        repo.saveAll(countries);
    }
}
