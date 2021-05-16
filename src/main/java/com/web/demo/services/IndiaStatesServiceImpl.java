package com.web.demo.services;

import com.web.demo.entities.IndiaStates;
import com.web.demo.repos.IndiaStatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndiaStatesServiceImpl implements IndiaStatesService {

    @Autowired
    private IndiaStatesRepo indiaStatesRepo;

    @Override
    public List<IndiaStates> getAllStates() {
        return indiaStatesRepo.findAll();
    }

    @Override
    public List<IndiaStates> findByStateNameIgnoreCase(String state) {
        return indiaStatesRepo.findByStateNameIgnoreCase(state);
    }
}
