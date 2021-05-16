package com.web.demo.services;

import com.web.demo.entities.*;
import com.web.demo.repos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private ICountriesRepository iCountriesRepository;

    @Autowired
    private ICropInsuranceRepository iCropInsuranceRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private IndiaStatesRepo indiaStatesRepo;

    @Autowired
    private ICityRepository repository;

    @Override
    public Iterable<CountriesEntity> getAllCountries() {
        return iCountriesRepository.findAll();
    }

    @Override
    public Iterable<CropInsurance> getAllCropInsurance() {
        return iCropInsuranceRepository.findAll();
    }

    @Override
    public List<CropInsurance> getByMandal(String mandal) {
        return iCropInsuranceRepository.findByMandalName(mandal);
    }

    @Override
    public List<CropInsurance> findByMandalNameIgnoreCase(String mandal) {
        return iCropInsuranceRepository.findByMandalNameIgnoreCase(mandal);
    }

    @Override
    public List<CropInsurance> findByMandalNameContainingIgnoreCase(String mandal) {
        return iCropInsuranceRepository.findByMandalNameContainingIgnoreCase(mandal);
    }

    @Override
    public List<CropInsurance> findByMandalNameIgnoreCaseAndCropIgnoreCase(String mandal, String crop) {
        return iCropInsuranceRepository.findByMandalNameIgnoreCaseAndCropIgnoreCase(mandal, crop);
    }

    @Override
    public List<CropInsurance> findByClaimAmountGreaterThanEqual(int start) {
        return iCropInsuranceRepository.findByClaimAmountGreaterThanEqual(start);
    }

    @Override
    public List<CropInsurance> findByClaimAmountBetweenOrderByClaimAmount(int start, int end) {
        return iCropInsuranceRepository.findByClaimAmountBetweenOrderByClaimAmount(start, end);
    }

    @Override
    public List<CropInsurance> findByClaimAmountGreaterThanEqualLessThanEqual(int start, int end) {
        return iCropInsuranceRepository.findByClaimAmountGreaterThanEqualAndClaimAmountLessThanEqualOrderByClaimAmount(start, end);
    }

    @Override
    public List<CropInsurance> findByClaimAmountInOrderByClaimAmount(List<Integer> claimAmount) {
        return iCropInsuranceRepository.findByClaimAmountInOrderByClaimAmount(claimAmount);
    }

    @Override
    public List<IndiaStates> getAllStates() {
        return indiaStatesRepo.findAll();
    }

    @Override
    public List<IndiaStates> findByStateNameIgnoreCase(String state) {
        return indiaStatesRepo.findByStateNameIgnoreCase(state);
    }

    @Async
    public CompletableFuture<List<Car>> saveCars(final InputStream inputStream) throws Exception {
        final long start = System.currentTimeMillis();

        List<Car> cars = parseCSVFile(inputStream);

        LOGGER.info("Saving a list of cars of size {} records", cars.size());

        cars = carRepository.saveAll(cars);

        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }

    private List<Car> parseCSVFile(final InputStream inputStream) throws Exception {
        final List<Car> cars = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(";");
                    final Car car = new Car();
                    car.setManufacturer(data[0]);
                    car.setModel(data[1]);
                    car.setType(data[2]);
                    cars.add(car);
                }
                return cars;
            }
        } catch (final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    @Override
    @Async
    public CompletableFuture<List<Car>> getAllCars() {

        LOGGER.info("Request to get a list of cars");

        final List<Car> cars = carRepository.findAll();
        return CompletableFuture.completedFuture(cars);
    }

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
