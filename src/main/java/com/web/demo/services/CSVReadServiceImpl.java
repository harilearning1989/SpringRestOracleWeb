package com.web.demo.services;

import com.web.demo.async.*;
import com.web.demo.dtos.*;
import com.web.demo.utils.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static com.web.demo.utils.ExecutorServiceUtil.getTheExecutorService;

@Service
public class CSVReadServiceImpl implements CSVReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReadServiceImpl.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<IndiaStatesDTO> getIndiaStates() {
        CompletableFuture<List<IndiaStatesDTO>> empFuture = supplyAsync(new ReadIndiaStatesSupplier(), getTheExecutorService());
        List<IndiaStatesDTO> empList = null;
        try {
            empList = empFuture.get();
            empList = Optional.ofNullable(empList)
                    .orElseGet(Collections::emptyList)
                    .parallelStream()
                    .filter(Objects::nonNull)
                    .filter(f -> !f.getStateName().contains("&"))
                    .filter(f -> f.getStateName() != null && f.getStateName().equalsIgnoreCase("HARYANA"))
                    .collect(Collectors.toList());
            return empList;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EmployeeDTO> readEmployeeInfo() {
        CompletableFuture<List<EmployeeDTO>> empFuture = supplyAsync(new ReadEmployeeSupplier());
        try {
            //TimeUnit.SECONDS.sleep(10);
            return empFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CropInsuranceDTO> readCropDetails() {
        CompletableFuture<List<CropInsuranceDTO>> cropFuture = supplyAsync(new ReadCropInsuranceSupplier());
        try {
            return cropFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CropInsuranceDTO> readCropByMandal(String mandalName) {
        CompletableFuture<List<CropInsuranceDTO>> cropFuture = supplyAsync(new ReadCropInsuranceSupplier());
        try {
            List<CropInsuranceDTO> basedOnMandal = cropFuture.get();

            return Optional.ofNullable(basedOnMandal)
                    .orElseGet(Collections::emptyList)
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(f -> f.getMandalName().equalsIgnoreCase(mandalName))
                    .collect(Collectors.toList());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentDTO> readStudentInfo() {
        CompletableFuture<List<StudentDTO>> cropFuture = supplyAsync(new ReadStudentSupplier());
        try {
            return cropFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CountriesDTO> readCountriesRegions() {
        CompletableFuture<List<CountriesDTO>> cropFuture = supplyAsync(new ReadCountriesSupplier());
        try {
            return cropFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalesOrderDTO> readSalesOrderDetails() {
        CompletableFuture<List<SalesOrderDTO>> cropFuture = supplyAsync(new ReadSalesOrderSupplier());
        try {
            return cropFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CropInsuranceDTO> readCsvFromClassPath() {
        Resource resource = resourceLoader.getResource("classpath:DataFiles/crop_insurance.csv");
        File file = null;
        try {
            file = resource.getFile();
            InputStream input = resource.getInputStream();
            List<CropInsuranceDTO> crops = CSVHelper.csvToCropInsurance(input);
            return crops;
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
