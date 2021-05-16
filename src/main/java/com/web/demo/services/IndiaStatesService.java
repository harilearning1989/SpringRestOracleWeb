package com.web.demo.services;

import com.web.demo.entities.IndiaStates;

import java.util.List;

public interface IndiaStatesService {
    List<IndiaStates> getAllStates();

    List<IndiaStates> findByStateNameIgnoreCase(String state);
}
