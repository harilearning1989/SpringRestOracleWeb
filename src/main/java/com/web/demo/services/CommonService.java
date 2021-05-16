package com.web.demo.services;

import com.web.demo.entities.*;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CommonService {

    public Iterable<CountriesEntity> getAllCountries();

    Iterable<CropInsurance> getAllCropInsurance();
    List<CropInsurance> getByMandal(String mandal);
    List<CropInsurance> findByMandalNameIgnoreCase(String mandal);
    List<CropInsurance> findByMandalNameContainingIgnoreCase(String mandal);
    List<CropInsurance> findByMandalNameIgnoreCaseAndCropIgnoreCase(String mandal,String crop);
    List<CropInsurance> findByClaimAmountGreaterThanEqual(int start);
    List<CropInsurance> findByClaimAmountBetweenOrderByClaimAmount(int start,int end);
    List<CropInsurance> findByClaimAmountGreaterThanEqualLessThanEqual(int start,int end);
    public List<CropInsurance> findByClaimAmountInOrderByClaimAmount(List<Integer> claimAmount);

    List<IndiaStates> getAllStates();

    List<IndiaStates> findByStateNameIgnoreCase(String state);

    public CompletableFuture<List<Car>> saveCars(InputStream inputStream) throws Exception;

    public CompletableFuture<List<Car>> getAllCars();

    List<City> findAllOrderByPopulationAsc();
    List<City> findAllOrderByNameAsc(String name);

}
