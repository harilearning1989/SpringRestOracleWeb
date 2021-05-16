package com.web.demo.services;

import com.web.demo.entities.Car;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    public CompletableFuture<List<Car>> saveCars(InputStream inputStream) throws Exception;

    public CompletableFuture<List<Car>> getAllCars();
}
