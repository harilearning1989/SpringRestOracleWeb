package com.web.demo.controls;

import com.web.demo.entities.Car;
import com.web.demo.entities.CountriesEntity;
import com.web.demo.entities.CropInsurance;
import com.web.demo.entities.IndiaStates;
import com.web.demo.services.CommonService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("common")
@Api(value = "CommonRestController")
public class CommonRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRestController.class);

    @Autowired
    private CommonService commonService;

    @GetMapping("/countries/list")
    public ResponseEntity<List<CountriesEntity>> getAllCountries() {
        try {
            List<CountriesEntity> countries = new ArrayList<CountriesEntity>();
            commonService.getAllCountries().forEach(countries::add);
            if (countries.isEmpty()) {
                System.out.println("SOUT");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllCountries :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/crops/list")
    public ResponseEntity<List<CropInsurance>> getAllCropInsurance() {
        try {
            List<CropInsurance> CropInsurance = new ArrayList<CropInsurance>();
            commonService.getAllCropInsurance().forEach(CropInsurance::add);
            if (CropInsurance.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(CropInsurance, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllCropInsurance :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/states/list")
    public ResponseEntity<List<IndiaStates>> getAllStates() {
        try {
            List<IndiaStates> indiaStates = commonService.getAllStates();
            if (indiaStates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(indiaStates, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception at getAllCropInsurance :=" + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/state")
    public List<IndiaStates> findByState(
            @RequestParam(defaultValue = "ANDHRA PRADESH") String state) {
        return commonService.findByStateNameIgnoreCase(state);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity uploadFile(
            @RequestParam(value = "files") MultipartFile[] files) {
        try {
            for (final MultipartFile file : files) {
                commonService.saveCars(file.getInputStream());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    CompletableFuture<ResponseEntity> getAllCars() {
        return commonService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetCarFailure);
    }

    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public @ResponseBody
    String getAllUsers(ModelMap model) {
        String jsonData = "[{\"id\":\"3253123\",\"firstname\":\"Chris\",\"lastname\":\"Johnson\",\"address\":\"211, Geoffrey Drive\",\"city\":\"Newark\",\"phone\":\"999-888-6666\",\"email\":\"chrisj@yahoo.com\"},{\"id\":\"67643837\",\"firstname\":\"Bill\",\"lastname\":\"Derkson\",\"address\":\"201, Sleepy Hollow Drive\",\"city\":\"Newark\",\"phone\":\"999-777-2222\",\"email\":\"billd@gmail.com\"}]";
        return jsonData;
    }

    @RequestMapping(value = "/getAllProfiles", method = RequestMethod.GET)
    public @ResponseBody
    String getAllProfiles(ModelMap model) {
        String jsonData = "[{\"firstname\":\"ajitesh\",\"lastname\":\"kumar\",\"address\":\"211/20-B,mgstreet\",\"city\":\"hyderabad\",\"phone\":\"999-888-6666\"},{\"firstname\":\"nidhi\",\"lastname\":\"rai\",\"address\":\"201,mgstreet\",\"city\":\"hyderabad\",\"phone\":\"999-876-5432\"}]";
        return jsonData;
    }
}
